package UHDBONF.service.logic;

import UHDBONF.domain.dto.LuckyDrawDto;
import UHDBONF.domain.dto.MessageDto;
import UHDBONF.domain.dto.UserDto;
import UHDBONF.service.LuckyDrawService;
import UHDBONF.service.MessageProcess;
import UHDBONF.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageProcessLogic implements MessageProcess {
    @Autowired
    private MessageService messageService;
    @Autowired
    private LuckyDrawService luckyDrawService;

    @Override
    public List<MessageDto> findAllOpenMessageByUserUid(String userUid) {
        List<MessageDto> messageDtoList = messageService.findAllOpenMessageByUserUid(userUid);

        messageDtoList.stream().forEach(messageDto -> {
            UserDto userDto = messageDto.getUser();
            List<LuckyDrawDto> luckyDrawDtoList = luckyDrawService.findAllOpenLuckyDrawByUserUid(userUid);
            userDto.setLuckyDraws(luckyDrawDtoList);
            messageDto.setUser(userDto);
        });

        return messageDtoList;
    }
}
