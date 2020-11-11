package com.javarush.task.task37.task3713.space.crew;

public abstract class AbstractCrewMember {
    public enum CompetencyLevel {
        NOVICE,
        INTERMEDIATE,
        ADVANCED,
        EXPERT
    }

    protected CompetencyLevel competencyLevel;

    protected AbstractCrewMember nextCrewMember;

    public void setNextCrewMember(AbstractCrewMember nextCrewMember) {
        this.nextCrewMember = nextCrewMember;
    }

    public void handleRequest(CompetencyLevel competencyLevel, String request) {
        if (this.competencyLevel == competencyLevel) {
            this.doTheJob(request);
            return;
        }
        this.nextCrewMember.handleRequest(competencyLevel, request);
    }

    protected abstract void doTheJob(String request);
}
