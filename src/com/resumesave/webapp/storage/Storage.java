package com.resumesave.webapp.storage;

import com.resumesave.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public interface Storage {

     void clear();

     void save(Resume resume);

     void update(Resume resume);

     Resume get(String uuid);

     void delete(String uuid);

     Resume[] getAll();

     int size();
}
