package ru.irlix_moviesearch.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.irlix_moviesearch.dto.CriticDTO;
import ru.irlix_moviesearch.model.Critic;
import ru.irlix_moviesearch.repository.CriticRepository;

import java.util.List;
import java.util.Optional;

/*Аннотация указывает, что тестовый класс будет использовать расширение Mockito
 * Это расширение упраавляет жизненным циклом МОК объектов*/
@ExtendWith(MockitoExtension.class)
class CriticServiceTest {

    /*Используется для создания МОК объекта, используется для подмены вызовов к реальному репозиторию*/
    @Mock
    private CriticRepository criticRepository;

    /*Указывает, что Mockito должен создать экземпляр класса клиентСервис и внедрить в него все моки*/
    @InjectMocks
    CriticService criticService;

    @Test
    void getAll() {

        List<Critic> critics = createCritic();
        /*Настройка мок объекта (критик репозиторий) таким образом,
        что при вызове метода findALL(), он возвращал заранее подготовленный список,
         т.е. имитирует поведение реального репозитория*/
        Mockito.when(criticRepository.findAll()).thenReturn(critics);

    /*Этот метод обращается к репозиторию и вызывает метод findAll(),
    затем преобразует список критиков в DTO*/
        List<CriticDTO> result = criticService.getAll();

        Assertions.assertEquals(critics.size(), result.size());
        Assertions.assertEquals(critics.get(0).getFirst_name(), result.get(0).getFirst_name());
        Assertions.assertEquals(critics.get(1).getLast_name(), result.get(1).getLast_name());
        Assertions.assertEquals(critics.get(2).getInfo(), result.get(2).getInfo());
    }

    @Test
    void getById() {

        Critic critic = createCritic().get(1);
        Mockito.when(criticRepository.findById(1L)).thenReturn(Optional.ofNullable(critic));
        CriticDTO criticDTO = criticService.getById(1L);
        Assertions.assertEquals(critic.getInfo(), criticDTO.getInfo());
    }

    @Test
    void create() {
        Critic critic = createCritic().get(1);
        Mockito.when(criticRepository.save(critic)).thenReturn(critic);
        criticService.create(critic);
        /*В данном случае, мы проверяем что через мок объект(criticService)
         метод save был вызван 1 раз*/
        Mockito.verify(criticRepository, Mockito.times(1)).save(critic);
    }

    @Test
    void update() {


        Critic critic = createCritic().get(0);
        critic.setFirst_name("Александр первый");

        /*Настраиваем mock для метода findById, чтобы он возвращал существующего критика*/
        Mockito.when(criticRepository.findById(critic.getId())).thenReturn(Optional.of(critic));

        /*Т.к. метод update использует метод save, то мы настраиваем mock для метода save,
        чтобы он соранял и возвращал существующего критика*/
        Mockito.when(criticRepository.save(critic)).thenReturn(critic);

        Critic result = criticService.update(critic.getId(), critic);

        Assertions.assertEquals("Александр первый", result.getFirst_name());
        Assertions.assertEquals("М", result.getLast_name());
    }

    @Test
    void delete() {

        /*doNothing указывает что метод deleteById не должен ничего возвращать*/
        Mockito.doNothing().when(criticRepository).deleteById(1L);

        /*Удаляем критика через сервис*/
        criticService.delete(1L);

        /*В данном случае, мы проверяем что через мок объект(clientService)
         метод delete был вызван 1 раз метод delete */
        Mockito.verify(criticRepository, Mockito.times(1)).deleteById(1L);
    }

    private List<Critic> createCritic() {
        Critic firstCritic = new Critic(1L, "Александр", "М", "Опытный кинокритик");
        Critic secondCritic = new Critic(2L, "Алексей", "К", "Известный кинокритик");
        Critic threeCritic = new Critic(3L, "Петр", "П", "Кинокритик журнала кинопоиск");
        Critic fourthCritic = new Critic(4L, "Василий", "В", "Блогер о кино");

        return List.of(firstCritic, secondCritic, threeCritic, fourthCritic);
    }
}