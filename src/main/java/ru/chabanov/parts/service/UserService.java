package ru.chabanov.parts.service;

import ru.chabanov.parts.model.Parts;

import java.util.List;

public interface UserService {
    void addParts (Parts parts);
    void updateParts(Parts parts);
    void deleteParts(int id);
    Parts getPartsById(int id);
    List<Parts> sortComps(int rowsPerPage, int page, int isNecessary);
    List<Parts> listComps(int rowsPerPage, int page, String criterion);
    long getCompsCount();
    long getCompsCount(int isNecessary);
    int getAmountOfFullComplect();
}
