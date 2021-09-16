package za.co.khoudnami.notekeeper

object DataManager {
    val courses = HashMap<String, CourseInfo>()
    val notes = ArrayList<NoteInfo>()
    // val recentlyViewedNotesList = ArrayList<NoteInfo>()

    init {
        initializeCourses()
        initializeNotes()
    }

    fun initializeNotes() {
//        addNote( // can also use this addNote() method, which was later defined in the testing course
//            CourseInfo("android_intents", "Android Programming with Intents"),
//            "Note 0",
//            "Text 0"
//        )

        notes.add(
            NoteInfo(
                CourseInfo("android_intents", "Android Programming with Intents"),
                "Note 0",
                "Text 0"
            )
        )
        notes.add(
            NoteInfo(
                CourseInfo("android_intents", "Android Programming with Intents"),
                "Note 1",
                "Text 1"
            )
        )
        notes.add(
            NoteInfo(
                CourseInfo("android_intents", "Android Programming with Intents"),
                "Note 2",
                "Text 2"
            )
        )
        notes.add(
            NoteInfo(
                CourseInfo("java_core", "Java Fundamentals: The Core Platform"),
                "Note 3",
                "Text 3"
            )
        )
        notes.add(
            NoteInfo(
                CourseInfo("java_lang", "Java Fundamentals: THe Java Language"),
                "Note 4",
                "Text 4"
            )
        )
        notes.add(
            NoteInfo(
                CourseInfo("java_core", "Java Fundamentals: The Core Platform"),
                "Note 5",
                "Text 5"
            )
        )
        notes.add(
            NoteInfo(
                CourseInfo("android_intents", "Android Programming with Intents"),
                "Note 6",
                "Text 6"
            )
        )

        notes.add(
            NoteInfo(
                CourseInfo("android_intents", "Android Programming with Intents"),
                "Note 7",
                "Text 7"
            )
        )
        notes.add(
            NoteInfo(
                CourseInfo("java_core", "Java Fundamentals: The Core Platform"),
                "Note 8",
                "Text 8"
            )
        )
        notes.add(
            NoteInfo(
                CourseInfo("android_async", "Android Async Programming and Services"),
                "Note 9",
                "Text 9"
            )
        )
        notes.add(
            NoteInfo(
                CourseInfo("android_async", "Android Async Programming and Services"),
                "Note 10",
                "Text 10"
            )
        )
    }

    private fun initializeCourses() {
        var course = CourseInfo("android_intents", "Android Programming with Intents")
        courses[course.courseId] = course

        course =
            CourseInfo(courseId = "android_async", title = "Android Async Programming and Services")
        courses[course.courseId] = course

        course = CourseInfo(title = "Java Fundamentals: THe Java Language", courseId = "java_lang")
        courses[course.courseId] = course

        course = CourseInfo("java_core", "Java Fundamentals: The Core Platform")
        courses.set(course.courseId, course)
    }

    fun addNote(note: NoteInfo): Int {
        notes.add(note)
        return notes.lastIndex
    }

    fun idOfNote(note: NoteInfo) = notes.indexOf(note)

    fun isLastNoteId(noteId: Int) = noteId == notes.lastIndex

    fun loadNote(noteId: Int) = notes[noteId]

    fun loadNotes(vararg noteIds: Int): List<NoteInfo> {
        simulateLoadDelay()
        val noteList: List<NoteInfo>

        if (noteIds.isEmpty())
            noteList = notes
        else {
            noteList = ArrayList<NoteInfo>(noteIds.size)
            for (noteId in noteIds)
                noteList.add(notes[noteId])
        }
        return noteList
    }

    fun noteIdsAsIntArray(notes: List<NoteInfo>): IntArray {
        val noteIds = IntArray(notes.size)
        for (index in 0..notes.lastIndex)
            noteIds[index] = DataManager.idOfNote(notes[index])
        return noteIds
    }

    private fun simulateLoadDelay() {
        Thread.sleep(1000)
    }

    /**
     * addNote() and findNote() were used in the Testing course
     * Adds a note to notes ArrayList<NoteInfo>
     */
    fun addNote(course: CourseInfo, noteTitle: String, noteText: String): Int {
        val note = NoteInfo(course, noteTitle, noteText)
        notes.add(note)
        return notes.lastIndex
    }

    fun findNote(course: CourseInfo, noteTitle: String, noteText: String): NoteInfo? {
        for (note in notes) {
            if (course == note.course && noteTitle == note.title && noteText == note.text) {
                return note
            }
        }
        return null
    }



}