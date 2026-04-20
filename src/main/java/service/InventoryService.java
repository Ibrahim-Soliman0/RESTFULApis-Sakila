package service;

import dto.InventoryDto;
import entity.Film;
import entity.Inventory;
import entity.Store;
import mapper.InventoryMapper;
import repository.InventoryRepository;
import repository.impl.InventoryRepositoryImpl;
import service.genaric.BaseService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class InventoryService extends BaseService<Inventory, Integer> {

    private final InventoryMapper inventoryMapper = InventoryMapper.INSTANCE;

    public InventoryService() {
        this(new InventoryRepositoryImpl());
    }

    public InventoryService(InventoryRepository inventoryRepository) {
        super(inventoryRepository);
    }

    public List<InventoryDto> getAllDtos(int page, int size) {
        return getAll(page, size).stream().map(inventoryMapper::toDto).collect(Collectors.toList());
    }

    public Optional<InventoryDto> getByIdDto(Integer id) {
        return getById(id).map(inventoryMapper::toDto);
    }

    public InventoryDto saveDto(InventoryDto dto) {
        Inventory entity = inventoryMapper.toEntity(dto);
        // Set relationships
        if (dto.getFilmId() != null) {
            Film film = new Film();
            film.setId(dto.getFilmId());
            entity.setFilm(film);
        }
        if (dto.getStoreId() != null) {
            Store store = new Store();
            store.setId(dto.getStoreId());
            entity.setStore(store);
        }
        Inventory saved = save(entity);
        return inventoryMapper.toDto(saved);
    }

    public Optional<InventoryDto> updateDto(Integer id, InventoryDto dto) {
        Optional<Inventory> existing = getById(id);
        if (existing.isPresent()) {
            Inventory entity = inventoryMapper.toEntity(dto);
            entity.setId(id);
            // Set relationships
            if (dto.getFilmId() != null) {
                Film film = new Film();
                film.setId(dto.getFilmId());
                entity.setFilm(film);
            }
            if (dto.getStoreId() != null) {
                Store store = new Store();
                store.setId(dto.getStoreId());
                entity.setStore(store);
            }
            Inventory updated = save(entity);
            return Optional.of(inventoryMapper.toDto(updated));
        }
        return Optional.empty();
    }
}