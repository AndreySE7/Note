package service

import Comment
import CommentNoFoundException
import Note
import org.junit.Assert.*
import org.junit.Test

class NoteServiceTest {

    private val service = NoteService()

    @Test
    fun add() {

        val note = Note("1", "1", 1, 1, "1", "1", 1,1,
            1,1, false, 1,false)
        val result = service.add(note)
        assertEquals(1, result)
    }

    @Test
    fun createComment() {

        val note = Note("1", "1", 1, 1, "1", "1", 1,1,
            1,1, false, 1,false)
        val comment = Comment(1, 1, 1, "1", 1, 1,1,false,1,false)
        service.add(note)
        service.createComment(comment)
        assertEquals(1, comment.commentId)
    }

    @Test
    fun noCreateComment() {

        val note = Note("1", "1", 1, 1, "1", "1", 1,1,
            1,1, false, 1,false)
        val comment = Comment(1, 1, 1, "1", 1, 2,1,false,1,false)
        service.add(note)
        service.createComment(comment)
        assertNotEquals(1, comment.commentId)
    }

    @Test
    fun deleteNote() {

        val note = Note("1", "1", 1, 1, "1", "1", 1,1,
            1,1, false, 1,false)
        service.add(note)
        val result = service.deleteNote(1)

        assertEquals(1, result)
    }

    @Test
    fun cantDeleteNote() {

        val note = Note("1", "1", 1, 1, "1", "1", 1,1,
            1,1, false, 1,false)
        service.add(note)
        val result = service.deleteNote(2)
        assertEquals(0, result)
    }

    @Test
    fun deleteComment() {

        val note = Note("1", "1", 1, 1, "1", "1", 1,2,
            1,1, false, 1,false)
        val comment = Comment(1, 1, 1, "1", 1, 1,1,false,1,false)
        service.add(note)
        service.createComment(comment)
        val result = service.deleteComment(1, 1)
        assertTrue(result)
    }

    @Test(expected = CommentNoFoundException::class)
    fun cantDeleteComment() {

        val note = Note("1", "1", 1, 1, "1", "1", 1,2,
            1,1, false, 1,false)
        val comment = Comment(1, 1, 1, "1", 1, 1,1,false,1,false)
        service.add(note)
        service.createComment(comment)
        service.deleteComment(3, 1)
    }

    @Test
    fun edit() {

        val note = Note("1", "1", 1, 1, "1", "1", 1,2,
            1,1, false, 1,false)
        service.add(note)
        val result = service.edit(1, "1", "1", "1", "1")
        assertTrue(result)
    }

    @Test
    fun editFalse() {

        val note = Note("1", "1", 1, 1, "1", "1", 1,2,
            1,1, false, 1,false)
        service.add(note)
        val result = service.edit(2, "1", "1", "1", "1")
        assertFalse(result)
    }

    @Test
    fun editComment() {

        val note = Note("1", "1", 1, 1, "1", "1", 1,1,
            1,1, false, 1,false)
        val comment = Comment(1, 1, 1, "1", 1, 1,1,false,1,false)
        service.add(note)
        service.createComment(comment)
        val result = service.editComment(1, 1,"11111")
        assertTrue(result)
    }

    @Test
    fun cantEditComment() {

        val note = Note("1", "1", 1, 1, "1", "1", 1,1,
            1,1, false, 1,false)
        val comment = Comment(2, 1, 1, "1", 1, 1,1,false,1,false)
        service.add(note)
        service.createComment(comment)
        val result = service.editComment(1, 1,"1")
        assertFalse(result)
    }

    @Test
    fun get() {

        val note1 = Note("1", "1", 1, 1, "1", "1", 1,2,
            1,1, false, 1,false)
        val note2 = Note("1", "1", 1, 1, "1", "1", 2,2,
            1,1, false, 1,false)
        service.add(note1)
        service.add(note2)
        val result = service.get(1, 1, 1, false)
        assertEquals(arrayListOf(note1, note2), result)
    }

    @Test
    fun getNo() {

        val note1 = Note("1", "1", 1, 1, "1", "1", 1,2,
            1,1, false, 1,false)
        val note2 = Note("1", "1", 1, 1, "1", "1", 2,2,
            1,1, false, 1,false)
        service.add(note1)
        service.add(note2)
        val result = service.get(1, 1, 1, false)
        assertEquals(arrayListOf(note1, note2), result)
    }

    @Test
    fun getById() {

        val note1 = Note("1", "1", 1, 1, "1", "1", 1,2,
            1,1, false, 1,false)
        val note2 = Note("1", "1", 1, 1, "1", "1", 2,2,
            1,1, false, 1,false)
        service.add(note1)
        service.add(note2)
        val result = service.getById(2, 1)
        assertEquals(note2, result)
    }

    @Test
    fun getNoById() {

        val note1 = Note("1", "1", 1, 1, "1", "1", 1,2,
            1,1, false, 1,false)
        val note2 = Note("1", "1", 1, 1, "1", "1", 2,2,
            1,1, false, 1,false)
        service.add(note1)
        service.add(note2)
        val result = service.getById(5, 1)
        assertNotEquals(note2, result)
    }

    @Test
    fun getComments() {

        val note1 = Note("1", "1", 1, 1, "1", "1", 1,2,
            1,1, false, 1,false)
        val note2 = Note("1", "1", 1, 1, "1", "1", 2,1,
            1,1, false, 1,false)
        service.add(note1)
        service.add(note2)
        val comment1 = Comment(1, 1, 1, "1", 1, 1,1,false,1,false)
        val comment2 = Comment(2, 1, 1, "1", 1, 1,1,false,1,false)
        service.createComment(comment1)
        service.createComment(comment2)
        val result = service.getComments(1, 1, false, 1, 1)
        assertNotEquals(mutableListOf(comment1, comment2), result)
    }

    @Test
    fun getNoComments() {

        val note1 = Note("1", "1", 1, 1, "1", "1", 1,1,
            1,1, false, 1,false)
        service.add(note1)
        val comment1 = Comment(1, 1, 1, "1", 1, 1,1,false,1,false)
        val comment2 = Comment(2, 1, 1, "1", 1, 1,1,false,1,false)
        service.createComment(comment1)
        service.createComment(comment2)
        val result = service.getComments(1, 1, false, 1, 1)
        assertNotEquals(mutableListOf(comment1, comment2), result)
    }

    @Test
    fun restoreComment() {

        val note1 = Note("1", "1", 1, 1, "1", "1", 1,1,
            1,1, false, 1,false)
        service.add(note1)
        val comment1 = Comment(1, 1, 1, "1", 1, 1,1,false,1,false)
        service.createComment(comment1)
        val result = service.restoreComment(1, 1)
        assertTrue(result)
    }

    @Test
    fun noRestoreComment() {

        val note1 = Note("1", "1", 1, 1, "1", "1", 1,1,
            1,1, false, 1,false)
        service.add(note1)
        val comment1 = Comment(2, 1, 1, "1", 1, 1,1,false,1,false)
        service.createComment(comment1)
        val result = service.restoreComment(1, 1)
        assertFalse(result)
    }

    @Test(expected = CommentNoFoundException::class)
    fun failRestoreComment() {

        val note1 = Note("1", "1", 1, 1, "1", "1", 1,1,
            1,1, false, 1,false)
        service.add(note1)
        val comment1 = Comment(1, 1, 1, "1", 1, 1,1,false,1,false)
        service.createComment(comment1)
        service.restoreComment(5, 1)
    }

}