import com.example.tuesdb.dtos.GroupDto;
import com.example.tuesdb.dtos.PermissionDto;
import com.example.tuesdb.dtos.UserDto;
import com.example.tuesdb.models.User;
import com.example.tuesdb.repositories.TuesRepository;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class Controller {

    private final TuesRepository tuesRepository;

    public Controller(TuesRepository tuesRepository) {
        this.tuesRepository = tuesRepository;
    }

    @GetMapping(value = "/users")
    public ResponseEntity<?> getAllUsers() {
        List<UserDto> userDtos = tuesRepository.getAllUsers()
                .stream()
                .map(UserDto::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(userDtos);
    }

    @PostMapping(value = "/user", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createUser(@RequestBody UserDto userInputDto) {
        // Assuming there's a method to convert UserInputDto to User
        User newUser = userInputDto.toUser();
        UserDto createdUserDto = new UserDto(tuesRepository.createUser(newUser));
        return ResponseEntity.ok(createdUserDto);
    }

    @GetMapping(value = "/groups")
    public ResponseEntity<?> getAllGroups() {
        List<GroupDto> groupDtos = tuesRepository.getAllGroups()
                .stream()
                .map(GroupDto::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(groupDtos);
    }

    @GetMapping(value = "/permissions")
    public ResponseEntity<?> getAllPermissions() {
        List<PermissionDto> permissionDtos = tuesRepository.getAllPermissions()
                .stream()
                .map(PermissionDto::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(permissionDtos);
    }
}
