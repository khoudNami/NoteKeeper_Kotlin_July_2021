package za.co.khoudnami.notekeeper

object DataManager {
    val courses = HashMap<String, CourseInfo>()
    val notes = ArrayList<NoteInfo>()

    init {
        initializeCourses()
        initializeNotes()
    }

    private fun initializeNotes() {
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
}