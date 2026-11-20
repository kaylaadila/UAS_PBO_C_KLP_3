package service;

import java.util.ArrayList;
import java.util.List;
import model.Grade;

/** Mengurus nilai siswa, rata-rata, dan predikat. */
public class GradeService extends BaseService<Grade> {
    private final SubjectService subjectService;

    /** Memuat data nilai dari file. */
    public GradeService(SubjectService subjectService) {
        super("grades.txt", Grade::fromFileString);
        this.subjectService = subjectService;
    }

    /** Menyimpan nilai (baru atau menimpa yang lama). @throws IllegalArgumentException jika mapel tidak ada */
    public void setGrade(String nis, String subjectId, double score) {
        if (subjectService.findSubject(subjectId) == null) throw new IllegalArgumentException("Mapel tidak ada.");
        for (Grade g : items) {
            if (g.getNis().equals(nis) && g.getSubjectId().equalsIgnoreCase(subjectId)) {
                g.setScore(score);
                save();
                return;
            }
        }
        items.add(new Grade(nis, subjectId.toUpperCase(), score));
        save();
    }

    /** @return semua nilai milik satu siswa */
    public List<Grade> getGradesOf(String nis) {
        List<Grade> result = new ArrayList<>();
        for (Grade g : items) if (g.getNis().equals(nis)) result.add(g);
        return result;
    }

    /** @return rata-rata nilai siswa (0 jika belum ada nilai) */
    public double getAverage(String nis) {
        return getGradesOf(nis).stream().mapToDouble(Grade::getScore).average().orElse(0);
    }

    /** Mencetak tabel nilai siswa beserta rata-rata dan predikatnya. */
    public void printGrades(String nis) {
        List<Grade> list = getGradesOf(nis);
        if (list.isEmpty()) {
            System.out.println("Belum ada nilai.");
            return;
        }
        System.out.printf("%-8s %-26s %7s %8s%n", "ID", "Mata Pelajaran", "Nilai", "Predikat");
        for (Grade g : list) {
            System.out.printf("%-8s %-26s %7.1f %8s%n", g.getSubjectId(),
                    subjectService.getSubjectName(g.getSubjectId()), g.getScore(), Grade.getPredicate(g.getScore()));
        }
        double avg = getAverage(nis);
        System.out.printf("Rata-rata: %.2f | Predikat: %s%n", avg, Grade.getPredicate(avg));
    }
}
