package com.javaops.webapp.storage;

import com.javaops.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {

    protected int getResumeIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    protected void fillDeletedElement(int resumeIndex) {
        storage[resumeIndex] = storage[size - 1];
    }

    @Override
    protected void insertElement(Resume r, int resumeIndex) {
        storage[size] = r;
    }
}
