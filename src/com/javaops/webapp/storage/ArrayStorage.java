package com.javaops.webapp.storage;

import com.javaops.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private Resume[] storage = new Resume[10000];
    private int size = 0;

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void update(Resume resume) {
        int resumeIndex = getResumeIndex(resume.getUuid());
        if (resumeIndex != -1) {
            storage[resumeIndex] = resume;
        } else {
            System.out.println("No resume with id " + resume.getUuid() + " found.");
        }
    }

    public void save(Resume r) {
        if (size == storage.length) {
            System.out.println("Storage limit reached. Please delete resume before adding new one.");
        } else {
            if (getResumeIndex(r.toString()) == -1) {
                storage[size++] = r;
            } else {
                System.out.println("Resume with id " + r.toString() + " already exists.");
            }
        }
    }

    public Resume get(String uuid) {
        int resumeIndex = getResumeIndex(uuid);
        if (resumeIndex != -1) {
            return storage[resumeIndex];
        }
        System.out.println("No resume with id " + uuid + " found.");
        return null;
    }

    public void delete(String uuid) {
        int resumeIndex = getResumeIndex(uuid);
        if (resumeIndex != -1) {
            for (int j = resumeIndex; j < size - 1; j++) {
                storage[j] = storage[j + 1];
            }
            storage[size - 1] = null;
            size--;
        } else {
            System.out.println("No resume with id " + uuid + " found.");
        }
    }

    private int getResumeIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].toString().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, size);
    }

    public int size() {
        return size;
    }
}
