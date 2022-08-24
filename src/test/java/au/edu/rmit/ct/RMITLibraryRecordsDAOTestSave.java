/**
 *
 * Name: John Smith (( Update with your name here ))
 * Student ID: s45045012  (( Update with your ID))
 *
 * [OPTIONAL: add any notes or comments here about the code]
 */

package au.edu.rmit.ct;

import com.wmw.examples.mockito.library.LibraryRecord;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class RMITLibraryRecordsDAOTestSave {

    //examples only.

    @Test
    @DisplayName("Test successful save")
    void checkSuccessfulSave() {
        RMITLibraryRecordsDAO lrdao = new RMITLibraryRecordsDAO();
        RMITLibraryItem book = new RMITLibraryItem(100L, "ISBN");
        LibraryRecord record = new LibraryRecord();
        record.setId(100L);
        record.setBook(book);
        record.setBorrowingDate(new Date());
        assertEquals(record.getBook(), book);
        assertTrue(lrdao.save(record));
    }

    @Test
    @DisplayName("Test save exceeding the record limit")
    void checkUnsuccessfulSave() {
        RMITLibraryRecordsDAO lrdao = new RMITLibraryRecordsDAO();
        RMITLibraryItem book = new RMITLibraryItem(100L, "ISBN");
        LibraryRecord record = new LibraryRecord();
        record.setId(100L);
        record.setBook(book);
        record.setBorrowingDate(new Date());
        assertEquals(record.getBook(), book);
        lrdao.setRecordLimit(0);
        assertTrue(lrdao.save(record));
        assertFalse(lrdao.save(record));
    }
}