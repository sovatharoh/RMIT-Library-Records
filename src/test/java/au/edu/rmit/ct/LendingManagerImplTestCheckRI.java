/**
 *
 * Name: Sovatharo Huy
 * Student ID: s3783867  (( Update with your ID))
 *
 * [OPTIONAL: add any notes or comments here about the code]
 */

package au.edu.rmit.ct;

import com.wmw.examples.mockito.library.LendingManagerImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LendingManagerImplTestCheckRI {
    @Test
    @DisplayName("Test unsuccessful checkRecordIntegrity failure from due to null borrowing date")
    void checkUnsuccessfulRecordIntegrity() {
        RMITLibraryRecordsDAO lrdao = new RMITLibraryRecordsDAO();
        LendingManagerImpl lendingManager = new LendingManagerImpl();
        RMITLibraryItem book = new RMITLibraryItem(100L, "ISBN");
        lendingManager.setLibraryRecordDAO(lrdao);
        lendingManager.borrowBook(book).setBorrowingDate(null);
        IllegalStateException exception = assertThrowsExactly(IllegalStateException.class, () -> lendingManager.returnBook(book));
        assertEquals(exception.getMessage(), "Empty borrowing date is found.");
    }

    @Test
    @DisplayName("Test unsuccessful checkRecordIntegrity failure from due to multiple returning dates null")
    void checkUnsuccessfulRecordIntegrity2(){
        RMITLibraryRecordsDAO lrdao = new RMITLibraryRecordsDAO();
        LendingManagerImpl lendingManager = new LendingManagerImpl();
        RMITLibraryItem book = new RMITLibraryItem(100L, "ISBN");
        RMITLibraryItem book2 = new RMITLibraryItem(200L, "ISBN");
        RMITLibraryItem book3 = new RMITLibraryItem(300L, "ISBN");
        lendingManager.setLibraryRecordDAO(lrdao);
        lendingManager.borrowBook(book);
        lendingManager.returnBook(book).setReturningDate(null);
        lendingManager.borrowBook(book2);
        lendingManager.returnBook(book2).setReturningDate(null);
        lendingManager.borrowBook(book3);
        IllegalStateException exception = assertThrowsExactly(IllegalStateException.class, () -> lendingManager.returnBook(book2));
        assertEquals(exception.getMessage(), "Multiple returning dates are empty.");
    }
}