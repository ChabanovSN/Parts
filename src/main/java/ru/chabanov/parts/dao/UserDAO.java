package ru.chabanov.parts.dao;

import ru.chabanov.parts.model.Parts;

import java.util.List;

public interface UserDAO {
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
