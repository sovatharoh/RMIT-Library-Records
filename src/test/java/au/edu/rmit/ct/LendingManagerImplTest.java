/**
 *
 * Name: Sovatharo Huy (( Update with your name here ))
 * Student ID: s3783867  (( Update with your ID))
 *
 * [OPTIONAL: add any notes or comments here about the code]
 */

package au.edu.rmit.ct;

import com.wmw.examples.mockito.library.LendingManagerImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

class LendingManagerImplTest {
    @Test
    @DisplayName("Test successful borrow transaction by checking the borrowed book is correct")
    void checkSuccessfulBorrowBook() {
        RMITLibraryRecordsDAO lrdao = new RMITLibraryRecordsDAO();
        LendingManagerImpl lendingManager = new LendingManagerImpl();
        RMITLibraryItem book = new RMITLibraryItem(100L, "ISBN");
        lendingManager.setLibraryRecordDAO(lrdao);
        assertEquals(lendingManager.borrowBook(book).getBook(), book);
    }

    @Test
    @DisplayName("Test successful borrow transaction by borrowing a book two times")
    void checkSuccessfulBorrowBook2() {
        RMITLibraryRecordsDAO lrdao = new RMITLibraryRecordsDAO();
        LendingManagerImpl lendingManager = new LendingManagerImpl();
        RMITLibraryItem book = new RMITLibraryItem(100L, "ISBN");
        lendingManager.setLibraryRecordDAO(lrdao);
        assertEquals(lendingManager.borrowBook(book).getBook(), book);
        assertEquals(lendingManager.returnBook(book).getBook(), book);
        assertEquals(lendingManager.borrowBook(book).getBook(), book);
    }

    @Test
    @DisplayName("Test successful borrow transaction by borrowing two different books")
    void checkSuccesfulBorrowBook3() {
        RMITLibraryRecordsDAO lrdao = new RMITLibraryRecordsDAO();
        LendingManagerImpl lendingManager = new LendingManagerImpl();
        RMITLibraryItem book = new RMITLibraryItem(100L, "ISBN");
        RMITLibraryItem book2 = new RMITLibraryItem(200L, "ISBN");
        lendingManager.setLibraryRecordDAO(lrdao);
        assertEquals(lendingManager.borrowBook(book).getBook(), book);
        assertEquals(lendingManager.borrowBook(book2).getBook(), book2);
    }
    @Test
    @DisplayName("Test successful return transaction by checking the returned book is correct")
    void checkSuccessfulReturnBook() {
        RMITLibraryRecordsDAO lrdao = new RMITLibraryRecordsDAO();
        LendingManagerImpl lendingManager = new LendingManagerImpl();
        RMITLibraryItem book = new RMITLibraryItem(100L, "ISBN");
        lendingManager.setLibraryRecordDAO(lrdao);
        assertEquals(lendingManager.borrowBook(book).getBook(), book);
        assertEquals(lendingManager.returnBook(book).getBook(), book);
    }

    @Test
    @DisplayName("Test successful return transaction by returning a book multiple times")
    void checkSuccessfulReturnBook2() {
        RMITLibraryRecordsDAO lrdao = new RMITLibraryRecordsDAO();
        LendingManagerImpl lendingManager = new LendingManagerImpl();
        RMITLibraryItem book = new RMITLibraryItem(100L, "ISBN");
        lendingManager.setLibraryRecordDAO(lrdao);
        assertEquals(lendingManager.borrowBook(book).getBook(), book);
        assertEquals(lendingManager.returnBook(book).getBook(), book);
        assertEquals(lendingManager.borrowBook(book).getBook(), book);
        assertEquals(lendingManager.returnBook(book).getBook(), book);
    }
    @Test
    @DisplayName("Test successful return transaction by borrowing two different books and then returning them")
    void checkSuccesfulReturnBook3() {
        RMITLibraryRecordsDAO lrdao = new RMITLibraryRecordsDAO();
        LendingManagerImpl lendingManager = new LendingManagerImpl();
        RMITLibraryItem book = new RMITLibraryItem(100L, "ISBN");
        RMITLibraryItem book2 = new RMITLibraryItem(200L, "ISBN");
        lendingManager.setLibraryRecordDAO(lrdao);
        assertEquals(lendingManager.borrowBook(book).getBook(), book);
        assertEquals(lendingManager.borrowBook(book2).getBook(), book2);
        assertEquals(lendingManager.returnBook(book).getBook(), book);
        assertEquals(lendingManager.returnBook(book2).getBook(), book2);
    }

    @Test
    @DisplayName("Test unsuccessful borrow transaction by borrowing a book that hasn't been returned")
    void checkUnsuccessfulBorrowBook() {
        RMITLibraryRecordsDAO lrdao = new RMITLibraryRecordsDAO();
        LendingManagerImpl lendingManager = new LendingManagerImpl();
        RMITLibraryItem book = new RMITLibraryItem(100L, "ISBN");
        lendingManager.setLibraryRecordDAO(lrdao);
        assertEquals(lendingManager.borrowBook(book).getBook(), book);
        IllegalStateException thrownException = assertThrowsExactly(IllegalStateException.class, () -> {
            lendingManager.borrowBook(book);
        });
        assertEquals(thrownException.getMessage(), "This book is not returned yet.");
    }

    @Test
    @DisplayName("Test unsuccessful return transaction by returning a book that hasn't been borrowed")
    void checkUnsuccessfulReturnBook(){
        RMITLibraryRecordsDAO lrdao = new RMITLibraryRecordsDAO();
        LendingManagerImpl lendingManager = new LendingManagerImpl();
        RMITLibraryItem book = new RMITLibraryItem(100L, "ISBN");
        lendingManager.setLibraryRecordDAO(lrdao);
        IllegalStateException thrownException = assertThrowsExactly(IllegalStateException.class, () -> {
            lendingManager.returnBook(book);
        });
        assertEquals(thrownException.getMessage(), "This book is not borrowed.");
    }
    @Test
    @DisplayName("Test unsuccessful borrow transaction by borrowing a book when record limit is reached")
    void checkUnsuccessfulReturnBook2(){
        RMITLibraryRecordsDAO lrdao = new RMITLibraryRecordsDAO();
        LendingManagerImpl lendingManager = new LendingManagerImpl();
        RMITLibraryItem book = new RMITLibraryItem(100L, "ISBN");
        lrdao.setRecordLimit(-1);
        lendingManager.setLibraryRecordDAO(lrdao);
        IllegalStateException thrownException = assertThrowsExactly(IllegalStateException.class, () -> {
            lendingManager.borrowBook(book);
        });
        assertEquals(thrownException.getMessage(), "New library record can't be saved.");
    }

    @Test
    @DisplayName("Test unsuccessful return transaction by returning a book when record limit is reached")
    void checkUnsuccessfulReturnBook3(){
        RMITLibraryRecordsDAO lrdao = new RMITLibraryRecordsDAO();
        LendingManagerImpl lendingManager = new LendingManagerImpl();
        RMITLibraryItem book = new RMITLibraryItem(100L, "ISBN");
        lrdao.setRecordLimit(0);
        lendingManager.setLibraryRecordDAO(lrdao);
        lendingManager.borrowBook(book);
        IllegalStateException thrownException = assertThrowsExactly(IllegalStateException.class, () -> {
            lendingManager.returnBook(book);
        });
        assertEquals(thrownException.getMessage(), "Library record can't be updated.");
    }
}