package mappers;

import dto.BoxDto;
import entity.Box;

public class BoxMapper {

    public static BoxDto entityToDto(Box box){
        BoxDto boxDto = new BoxDto();
        boxDto.setId(box.getId());
        boxDto.setValability(box.getValability());
        return boxDto;
    }
}
