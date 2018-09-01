package com.app.oauth2.repository;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class Week {

    /* 週の開始時刻 */
    private String w;

    /* 追加数 */
    private Long a;

    /* 削除数 */
    private Long d;

    /* コミット数 */
    private Long c;
}
