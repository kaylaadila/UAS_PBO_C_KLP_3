package service;

/** Wadah semua service agar satu objek yang sama dipakai bersama oleh seluruh menu. */
public class AppContext {
    private final AuthService auth = new AuthService();
    private final StudentService students = new StudentService();
    private final ClassService classes = new ClassService();
    private final SubjectService subjects = new SubjectService();
    private final GradeService grades = new GradeService(subjects);
