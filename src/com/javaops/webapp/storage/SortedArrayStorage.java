package com.javaops.webapp.storage;

import com.javaops.webapp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {
    @Override
    protected int getResumeIndex(String uuid) {
        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }

    @Override
    protected void fillDeletedElement(int resumeIndex) {
        int numMoved = size - resumeIndex - 1;
        if (numMoved > 0) {
            System.arraycopy(storage, resumeIndex + 1, storage, resumeIndex, numMoved);
        }

    }

    @Override
    protected void insertElement(Resume r, int resumeIndex) {
        int insertIdx = -resumeIndex - 1;
        System.arraycopy(storage, insertIdx, storage, insertIdx + 1, size - insertIdx);
        storage[insertIdx] = r;
    }
}
