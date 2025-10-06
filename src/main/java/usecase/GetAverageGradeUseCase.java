package usecase;

import api.GradeDataBase;
import entity.Grade;
import entity.Team;

/**
 * GetAverageGradeUseCase class.
 */
public final class GetAverageGradeUseCase {
    private final GradeDataBase gradeDataBase;

    public GetAverageGradeUseCase(GradeDataBase gradeDataBase) {
        this.gradeDataBase = gradeDataBase;
    }

    /**
     * Get the average grade for a course across your team.
     * @param course The course.
     * @return The average grade.
     */
    public float getAverageGrade(String course) {
        // Get the team information
        Team myTeam = gradeDataBase.getMyTeam();

        // Get all team member usernames
        String[] teamMembers = myTeam.getMembers();

        float totalGrade = 0.0f;
        int count = 0;

        // Loop through each team member and get their grade
        for (String username : teamMembers) {
            Grade grade = gradeDataBase.getGrade(username, course);

            if (grade != null && grade.getGrade() >= 0) {
                totalGrade += grade.getGrade();
                count++;
            }
        }

        // Calculate and return average
        if (count > 0) {
            return totalGrade / count;
        } else {
            return 0.0f;
        }
    }
}
