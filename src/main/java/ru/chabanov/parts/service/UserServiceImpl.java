package ru.chabanov.parts.service;

import org.springframework.stereotype.Service;
import ru.chabanov.parts.dao.UserDAO;
import ru.chabanov.parts.model.Parts;

import javax.transaction.Transactional;
import java.util.List;

@Service(value = "userService")
public class UserServiceImpl implements  UserService {

    private UserDAO userDAO;

    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }
    @Transactional
    public void addParts(Parts parts) {
        userDAO.addParts(parts);

    }
    @Transactional
    public void updateParts(Parts parts) {
       userDAO.updateParts(parts);
    }
    @Transactional
    public void deleteParts(int id) {
       userDAO.deleteParts(id);
    }
    @Transactional
    public Parts getPartsById(int id) {
        return userDAO.getPartsById(id);
    }

    @Transactional
    public List<Parts> sortComps(int rowsPerPage, int page, int isNecessary) {
        return userDAO.sortComps(rowsPerPage,page,isNecessary);
    }

    @Transactional
    public List<Parts> listComps(int rowsPerPage, int page, String criterion) {
        return userDAO.listComps(rowsPerPage,page,criterion);
    }
    @Transactional
    public long getCompsCount() {
        return userDAO.getCompsCount();
    }
    @Transactional
    public long getCompsCount(int isNecessary) {
        return userDAO.getCompsCount(isNecessary);
    }
    @Transactional
    public int getAmountOfFullComplect() {
        return userDAO.getAmountOfFullComplect();
    }
}
