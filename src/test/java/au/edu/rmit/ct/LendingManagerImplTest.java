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
    /*
    TODO
    Provide at least four differently purposed Junit test cases that cover successful borrow and
    return transactions (ie 2 x 2 overall)
     */
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
    @DisplayName("Test successful borrow transaction by checking that the borrowed date is correct")
    void checkSuccessfulBorrowBook2() {
        RMITLibraryRecordsDAO lrdao = new RMITLibraryRecordsDAO();
        LendingManagerImpl lendingManager = new LendingManagerImpl();
        RMITLibraryItem book = new RMITLibraryItem(100L, "ISBN");
        lendingManager.setLibraryRecordDAO(lrdao);
        assertEquals(lendingManager.borrowBook(book).getBorrowingDate(), new Date());
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
    @DisplayName("Test successful return transaction by checking the returned date is correct")
    void checkSuccessfulReturnBook2() {
        RMITLibraryRecordsDAO lrdao = new RMITLibraryRecordsDAO();
        LendingManagerImpl lendingManager = new LendingManagerImpl();
        RMITLibraryItem book = new RMITLibraryItem(100L, "ISBN");
        lendingManager.setLibraryRecordDAO(lrdao);
        assertEquals(lendingManager.borrowBook(book).getBorrowingDate(), new Date());
        assertEquals(lendingManager.returnBook(book).getReturningDate(), new Date());
    }
    /*
    TODO
    Provide at least two differently purposed Junit test cases for unsuccessful borrow / return transactions.
     */
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
}