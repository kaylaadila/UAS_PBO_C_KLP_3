package service;

/** Wadah semua service agar satu objek yang sama dipakai bersama oleh seluruh menu. */
public class AppContext {
    private final AuthService auth = new AuthService();
    private final StudentService students = new StudentService();
    private final ClassService classes = new ClassService();
    private final SubjectService subjects = new SubjectService();
    private final GradeService grades = new GradeService(subjects);
private final RegistrationService registrations = new RegistrationService();

    /** @return service akun */
    public AuthService getAuth() { return auth; }
    /** @return service siswa */
    public StudentService getStudents() { return students; }
    /** @return service kelas */
    public ClassService getClasses() { return classes; }
    /** @return service mata pelajaran */
    public SubjectService getSubjects() { return subjects; }
    /** @return service nilai */
    public GradeService getGrades() { return grades; }
    /** @return service pendaftaran */
    public RegistrationService getRegistrations() { return registrations; }
}
