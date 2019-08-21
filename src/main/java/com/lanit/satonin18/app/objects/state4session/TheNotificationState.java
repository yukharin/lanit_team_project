package com.lanit.satonin18.app.objects.state4session;

import com.lanit.satonin18.app.objects.input.form.OrderByForm;
import com.lanit.satonin18.app.objects.input.form.PaginationForm;
import lombok.Data;

import java.io.Serializable;

//TODO (как сделать так чтобы были свои обьекты(а не дто, у которых пришлось сетеры еще поставить),
// и чтоб переменные были только в одном месте(без дублирование кода при будущих изменениях),
// и чтоб другому разрабу мое будущие решение было сразу же понятно, а не спустя 10 минут)
@Data
public class TheNotificationState
        implements Serializable {

    PaginationForm paginationForm;
    OrderByForm orderByForm;
}
