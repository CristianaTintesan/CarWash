package service;

import dto.BoxDto;
import entity.Box;
import exception.CustomErrorMessages;
import exception.EntityNotExistException;
import mappers.BoxMapper;
import repository.BoxRepo;
import utils.ApplicationUtils;

public class BoxService {

    private BoxRepo boxRepo;

    public BoxService() {
        this.boxRepo = new BoxRepo();
    }

    public BoxDto getBox(String id) {
        if (id == null || id.equals("") || id.length() < 36) {
            throw new IllegalArgumentException(CustomErrorMessages.INVALID_ID_MESSAGE);
        }

        Box box = boxRepo.findBox(id);

        if (box == null) {
            throw new EntityNotExistException("No box having id " + id + "exists.");
        }

        return BoxMapper.entityToDto(box);
    }

    public void addBox(Box box) {
        int id = boxRepo.lastId() + 1;
        box.setId(Integer.toString(id));
        boxRepo.insertNewBox(box);
    }

    public void removeBox(String id){
        boxRepo.removeBoxRep(id);
    }
}
