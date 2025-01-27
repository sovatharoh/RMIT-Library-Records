/**
 *
 * Name: Sovatharo Huy
 * Student ID: s3783867
 *
 * [OPTIONAL: add any notes or comments here about the code]
 */

package au.edu.rmit.ct;

import com.wmw.examples.mockito.library.Book;
import com.wmw.examples.mockito.library.LendingManagerImpl;
import com.wmw.examples.mockito.library.LibraryRecord;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class RMITLibraryRecordsDAOTestFindByBook {

    //examples only.

    @Test
    @DisplayName("Test successful find by book of class RMITLibraryItem")
    void checkSuccessfulFindByBook() {
        RMITLibraryRecordsDAO lrdao = new RMITLibraryRecordsDAO();
        RMITLibraryItem book = new RMITLibraryItem(100L, "ISBN");
        LibraryRecord record = new LibraryRecord();
        record.setId(100L);
        record.setBook(book);
        record.setBorrowingDate(new Date());
        assertEquals(record.getBook(), book);
        assertTrue(lrdao.save(record));
        assertEquals(lrdao.findByBook(book).get(0), record);
    }

    @Test
    @DisplayName("Test successful find by book by searching empty records list which should return empty list")
    void checkSuccessfulFindByBook2(){
        RMITLibraryRecordsDAO lrdao = new RMITLibraryRecordsDAO();
        RMITLibraryItem book = new RMITLibraryItem(100L, "ISBN");
        assertEquals(lrdao.findByBook(book).size(), 0);
    }
    @Test
    @DisplayName("Test find by book by searching with book not in records list should return empty list")
    void checkSuccessfulFindByBook3(){
        RMITLibraryRecordsDAO lrdao = new RMITLibraryRecordsDAO();
        RMITLibraryItem book = new RMITLibraryItem(100L, "ISBN");
        RMITLibraryItem book2 = new RMITLibraryItem(200L, "ISBN");
        LibraryRecord record = new LibraryRecord();
        record.setId(100L);
        record.setBook(book);
        record.setBorrowingDate(new Date());
        assertEquals(record.getBook(), book);
        assertTrue(lrdao.save(record));
        assertEquals(lrdao.findByBook(book2).size(), 0);
    }
    @Test
    @DisplayName("Test unsuccessful find by book by searching with book of class Book")
    void checkUnsuccessfulFindByBook(){
        RMITLibraryRecordsDAO lrdao = new RMITLibraryRecordsDAO();
        Book book = new Book();
        LibraryRecord record = new LibraryRecord();
        record.setId(100L);
        record.setBook(book);
        record.setBorrowingDate(new Date());
        assertEquals(record.getBook(), book);
        assertTrue(lrdao.save(record));
        IllegalArgumentException exception = assertThrowsExactly(IllegalArgumentException.class, () -> {
            lrdao.findByBook(book);
        });
        assertEquals("Book class usage suspended. Expecting RMITLibraryItem.class", exception.getMessage());
    }
}
