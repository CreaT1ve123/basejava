package com.javaops.webapp.storage;

import com.javaops.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public abstract class AbstractArrayStorage implements Storage {
    protected static final int STORAGE_LIMIT = 10000;
    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void update(Resume resume) {
        int resumeIndex = getResumeIndex(resume.getUuid());
        if (resumeIndex < 0) {
            System.out.println("No resume with id " + resume.getUuid() + " found.");
        } else {
            storage[resumeIndex] = resume;
        }
    }

    public void save(Resume r) {
        int resumeIndex = getResumeIndex(r.getUuid());
        if (size == STORAGE_LIMIT) {
            System.out.println("Storage limit reached. Please delete resume before adding new one.");
        } else {
            if (resumeIndex < 0) {
                insertElement(r, resumeIndex);
                size++;
            } else {
                System.out.println("Resume with id " + r.getUuid() + " already exists.");
            }
        }
    }

    public void delete(String uuid) {
        int resumeIndex = getResumeIndex(uuid);
        if (resumeIndex < 0) {
            System.out.println("No resume with id " + uuid + " found.");
        } else {
            fillDeletedElement(resumeIndex);
            storage[size - 1] = null;
            size--;
        }
    }

    public Resume get(String uuid) {
        int resumeIndex = getResumeIndex(uuid);
        if (resumeIndex >= 0) {
            return storage[resumeIndex];
        }
        System.out.println("No resume with id " + uuid + " found.");
        return null;
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

    protected abstract int getResumeIndex(String uuid);

    protected abstract void fillDeletedElement(int resumeIndex);

    protected abstract void insertElement(Resume r, int resumeIndex);
}
