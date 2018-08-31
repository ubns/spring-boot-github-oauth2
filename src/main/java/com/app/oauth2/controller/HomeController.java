package com.app.oauth2.controller;

import com.app.oauth2.repository.Repository;
import com.app.oauth2.service.RepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.Locale;

@Controller
public class HomeController {

    @Autowired
    private MessageSource ms;

    @Autowired
    private RepositoryService service;

    /**
     * トップページ繊維処理
     * <P>トップページ（ホーム）へ繊維する。</P>
     * @param model
     * @param locale
     * @return トップページ画面
     */
    @GetMapping("/")
    public String home(Model model, Locale locale) {
        model.addAttribute("title", ms.getMessage("page.title.top", null, locale));
        return "home";
    }

    /**
     * リポジトリ情報取得処理
     * <P>ユーザのリポジトリ情報を取得して表示する。</P>
     * @param name
     * @param model
     * @param principal
     * @param attributes
     * @return ダッシュボード
     */
    @GetMapping("/{username}")
    public String login(@PathVariable("username") String name, Model model, Principal principal, RedirectAttributes attributes, Locale locale) {

        /* 認証チェック */
        if (principal == null) {
            attributes.addFlashAttribute("error", ms.getMessage("error.message.token", null, locale));
            return "redirect:/";
        }

        Authentication auth = (Authentication)principal;
        String userName = (String)auth.getPrincipal();

        /* URLチェック */
        if (!name.equals(userName)) {
            attributes.addFlashAttribute("error", ms.getMessage("error.message.url", null, locale));
            return "redirect:/";
        }

        // リポジトリ情報取得
        Repository[] list = service.getUserRepo();

        model.addAttribute("repos", list);
        model.addAttribute("title", ms.getMessage("page.title.dashboard", new String[]{name}, locale));
        return "user/dashboard";
    }
}
