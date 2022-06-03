package service

import Comment
import CommentNoFoundException
import Note

class NoteService {
    private var notes = mutableMapOf<Note, MutableList<Comment?>>()
    private var id = 1

    fun add(note: Note): Int {
        note.noteId = id
        id++
        notes[note] = mutableListOf()
        return note.noteId
    }

    fun createComment(comment: Comment): Int {
        for (note in notes) {
            if (comment.noteId == note.key.noteId) {
                note.value.add(comment)
                return comment.commentId
            }
        }
        return 0
    }

    fun deleteNote(noteId: Int): Int {

        for (n in notes) {
            if (noteId == n.key.noteId) {
                notes.remove(n.key)
                return 1
            }
        }
        return 0
    }


    fun deleteComment(commentId: Int, ownerId: Int): Boolean {
        for (value in notes.values) {
            for (message in value) {
                if (message != null) {
                    if (message.commentId == commentId) {
                        return if (!message.deleted) {
                            message.deleted
                            true
                        } else {
                            false
                        }
                    }
                }
            }
        }
        throw CommentNoFoundException()
    }

    fun edit(noteId: Int, title: String, text: String, privacyView: String, privacyComment: String): Boolean {
        for (note in notes) {
            if (noteId == note.key.noteId) {
                note.key.title = title
                note.key.text = text
                note.key.privacyView = privacyView
                note.key.privacyComment = privacyComment
                return true
            }
        }
        return false
    }

    fun editComment(commentId: Int, ownerId: Int, message: String): Boolean {
        for (value in notes.values) {
            for (comment in value) {
                if (comment != null && comment.commentId == commentId) {
                    return if (!comment.deleted) {
                        comment.message = message
                        true
                    } else {
                        false
                    }
                }
            }
        }
        return false
    }

    fun get(userId: Int, offset: Int, count: Int, sort: Boolean): MutableList<Note> {
        val listOfUserComments: MutableList<Note> = mutableListOf()
        for (note in notes.keys) {
            if (note.ownerId == userId) {
                listOfUserComments.add(note)
            }
        }
        return listOfUserComments
    }


    fun getById(noteId: Int, ownerId: Int, needWiki: Boolean = false): Note? {
        for (note in notes) {
            if (noteId == note.key.noteId) {
                return note.key
            }
        }
        return null
    }

    fun getComments(noteId: Int, ownerId: Int, sort: Boolean = false, offset: Int, count: Int): MutableList<Comment?>? {
        for (note in notes) {
            if (noteId == note.key.noteId) {
                return note.value
            }
        }
        return null
    }

    fun restoreComment(commentId: Int, noteId: Int): Boolean {
        for (note in notes) {
            if (noteId == note.key.noteId) {
                for (comment in note.value) {
                    if (comment != null && comment.commentId == commentId) {
                        if (comment.deleted) {
                        }
                        comment.deleted = false
                        return true
                    } else {
                        throw CommentNoFoundException()
                    }
                }
            }
        }
        return false
    }

}