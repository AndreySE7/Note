import service.NoteService

fun main() {
    val note1 = Note("1", "1", 1, 1, "1", "1", 1,2,
        1,1, false, 1,false)
    NoteService().add(note1)

    val comment1 = Comment(1, 1, 1, "1", 1, 1,1,false,1,false)
    NoteService().createComment(comment1)

    println(note1)
    println(comment1)
}