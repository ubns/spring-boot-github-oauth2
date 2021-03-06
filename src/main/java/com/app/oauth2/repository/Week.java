package com.app.oauth2.repository;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;

@Data
@Component
public class Week {

    /* 週の開始時刻 */
    private Long w;

    /* 追加数 */
    private Long a;

    /* 削除数 */
    private Long d;

    /* コミット数 */
    private Long c;

    public Date getW() {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(this.w);
        return cal.getTime();
    }
}
