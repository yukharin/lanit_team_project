package com.lanit.satonin18.app.objects.state4session;

import com.lanit.satonin18.app.objects.input.form.FilterForm;
import com.lanit.satonin18.app.objects.input.form.OrderByForm;
import com.lanit.satonin18.app.objects.input.form.PaginationForm;
import lombok.Data;

import java.io.Serializable;

//@Data
//public class CabinetSessionState
//        implements Serializable {
//
//    FilterDto filterDto;
//    PaginationDto paginationDto;
//    OrderByDto orderByDto;
//}
//TODO (как сделать так чтобы были свои обьекты(а не дто, у которых пришлось сетеры еще поставить),
// и чтоб переменные(дто и своих обьектов) были только в одном месте(без дублирование кода при будущих изменениях),
// и чтоб другому разрабу мое будущие решение было сразу же понятно, а не спустя 10 минут)
// -> наверн адаптер поставть (или перименовть dto, чтоб не вводить в заблудение)
// -> ПОКА ПРОСТ ПЕРЕИМЕНУЮ Dto-Form (чтоб не вводить в заблуждение)
@Data
public class CabinetSessionState
        implements Serializable {

    FilterForm filterForm;
    PaginationForm paginationForm;
    OrderByForm orderByForm;
}
