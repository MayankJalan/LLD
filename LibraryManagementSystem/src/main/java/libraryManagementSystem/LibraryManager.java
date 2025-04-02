package libraryManagementSystem;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class LibraryManager {
    private static LibraryManager instance;
    Map<String, Member> members;
    Map<String, Book> catalog;
    private final int MAX_BOOKS_PER_MEMBER = 5;
    private final int LOAN_DURATION_DAYS = 14;


    public LibraryManager() {
        catalog = new ConcurrentHashMap<>();
        members = new ConcurrentHashMap<>();
    }

    public static synchronized LibraryManager getInstance() {
        if (instance == null) {
            instance = new LibraryManager();
        }
        return instance;
    }

    public void addBook(Book book) {
        catalog.put(book.getIsbn(), book);
    }

    public void removeBook(String isbn) {
        catalog.remove(isbn);
    }

    public Book getBook(String isbn) {
        return catalog.get(isbn);
    }

    public void registerMember(Member member) {
        members.put(member.getMemberId(), member);
    }

    public void unregisterMember(String memberId) {
        members.remove(memberId);
    }

    public Member getMember(String memberId) {
        return members.get(memberId);
    }

    public synchronized void borrowBook(String memberId, String isbn) {
        Member member = getMember(memberId);
        Book book = getBook(isbn);
        if (member != null && book != null && book.isAvailable()) {
            if (member.getBorrowedBooks().size() < MAX_BOOKS_PER_MEMBER) {
                member.borrowBook(book);
                book.setAvailable(false);
            } else {
                System.out.println("Member " + member.getName() + " has reached the maximum number of borrowed books.");
            }
        } else {
            System.out.println("Book or member not found, or book is not available.");
        }
    }

    public synchronized void returnBook(String memberId, String isbn) {
        Member member = members.get(memberId);
        Book book = catalog.get(isbn);
        if (member != null && book != null) {
            member.returnBook(book);
            book.setAvailable(true);
            System.out.println("Book returned: " + book.getName() + " by " + member.getName());
        } else {
            System.out.println("Book or member not found.");
        }
    }

    public List<Book> searchBooks(String keyword) {
        return catalog.values().stream().filter(book ->
                        book.getName().contains(keyword) ||
                        book.getAuthor().contains(keyword)).collect(Collectors.toList());
    }


}
