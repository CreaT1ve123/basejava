package com.resumesave.webapp.storage;

import com.resumesave.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage implements Storage {
    public static final int STORAGE_LIMIT = 10000;
    private Resume[] storage = new Resume[STORAGE_LIMIT];
    private int size = 0;

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void save(Resume resume) {
        int index = isResumeInStorage(resume.uuid);
        if(index == -1 && size < storage.length) {
            storage[size] = resume;
            size++;
        } else if(index != -1){
            System.out.println("The resume with uuid "+resume.uuid+" already exists.");
        } else if(size >= STORAGE_LIMIT){
            System.out.println("The storage limit is exceeded.");
        }
    }

    public void update(Resume resume){
        int index = isResumeInStorage(resume.uuid);
        if(index != -1) {
            storage[index] = resume;
        } else{
            System.out.println("The resume with uuid "+resume.uuid+" doesn't exist.");
        }
    }

    public Resume get(String uuid) {
        int index = isResumeInStorage(uuid);
        if(index >= 0) {
            return storage[index];
        } else{
            System.out.println("The resume with uuid "+uuid+" doesn't exist.");
        }
        return null;
    }

    public void delete(String uuid) {
        int index = isResumeInStorage(uuid);
        if(index >= 0) {
            storage[index] = storage[size - 1];
            storage[size - 1] = null;
            size--;
        } else{
            System.out.println("The resume with uuid "+uuid+" doesn't exist.");
        }
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

    private int isResumeInStorage(String uuid){
        for (int i = 0; i < size; i++) {
            if(storage[i].uuid == uuid) return i;
        }
        return -1;
    }
}
