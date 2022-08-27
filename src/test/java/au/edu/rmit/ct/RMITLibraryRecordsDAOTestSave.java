/**
 *
 * Name: Sovatharo Huy
 * Student ID: s3783867
 *
 *  LibraryRecordsDao should not allow the saving of null values. checkUnsuccessfulSaveNullValue() test is intended to
 *  fail until this is fixed.
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
    @DisplayName("Test unsuccessful save exceeding the record limit")
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

    @Test
    @DisplayName("Test unsuccessful save with null record")
    void checkUnsuccessfulSaveNullValue() {
        RMITLibraryRecordsDAO lrdao = new RMITLibraryRecordsDAO();
        assertFalse(lrdao.save(null));
    }
}