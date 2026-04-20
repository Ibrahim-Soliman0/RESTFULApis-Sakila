package service;

import dto.ActorDto;
import entity.Actor;
import mapper.ActorMapper;
import repository.ActorRepository;
import repository.impl.ActorRepositoryImpl;
import service.genaric.BaseService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ActorService extends BaseService<Actor, Integer> {

    private final ActorMapper actorMapper = ActorMapper.INSTANCE;

    public ActorService() {
        this(new ActorRepositoryImpl());
    }

    public ActorService(ActorRepository actorRepository) {
        super(actorRepository);
    }

    public List<ActorDto> getAllDtos(int page, int size) {
        return getAll(page, size).stream()
                .map(actorMapper::toDto)
                .collect(Collectors.toList());
    }

    public Optional<ActorDto> getByIdDto(Integer id) {
        return getById(id).map(actorMapper::toDto);
    }

    public ActorDto saveDto(ActorDto dto) {
        Actor entity = actorMapper.toEntity(dto);
        Actor saved = save(entity);
        return actorMapper.toDto(saved);
    }

    public Optional<ActorDto> updateDto(Integer id, ActorDto dto) {
        Optional<Actor> existing = getById(id);
        if (existing.isPresent()) {
            Actor entity = actorMapper.toEntity(dto);
            entity.setId(id);
            Actor updated = save(entity);
            return Optional.of(actorMapper.toDto(updated));
        }
        return Optional.empty();
    }
}
