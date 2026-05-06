package backend;

import entity.Officer;

import java.util.ArrayList;
import java.util.List;

public class QLCB {
    private final List<Officer> officers = new ArrayList<>();

    public void addOfficer(Officer officer) {
        if (officer == null) {
            return;
        }
        officers.add(officer);
    }

    public List<Officer> searchOfficersByName(String name) {
        List<Officer> result = new ArrayList<>();
        if (name == null || name.trim().isEmpty()) {
            return result;
        }

        String keyword = name.trim().toLowerCase();
        for (Officer officer : officers) {
            if (officer.getName() != null && officer.getName().toLowerCase().contains(keyword)) {
                result.add(officer);
            }
        }
        return result;
    }

    public List<String> getOfficerDetails() {
        List<String> details = new ArrayList<>();
        for (Officer officer : officers) {
            details.add(officer.getDisplayInfo());
        }
        return details;
    }

    public boolean deleteOfficerByName(String name) {
        if (name == null || name.trim().isEmpty()) {
            return false;
        }

        String targetName = name.trim();
        for (int i = 0; i < officers.size(); i++) {
            Officer officer = officers.get(i);
            if (officer.getName() != null && officer.getName().equalsIgnoreCase(targetName)) {
                officers.remove(i);
                return true;
            }
        }
        return false;
    }

    public int getOfficerCount() {
        return officers.size();
    }
}