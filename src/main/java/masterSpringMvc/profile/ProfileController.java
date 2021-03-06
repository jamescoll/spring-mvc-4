package masterSpringMvc.profile;

import java.util.Locale;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import masterSpringMvc.date.USLocalDateFormatter;

import javax.validation.Valid;
import org.springframework.validation.BindingResult;
import javax.servlet.http.HttpServletRequest;


@Controller
public class ProfileController {

	@RequestMapping("/profile")
	public String displayProfile(ProfileForm profileForm){
		return "profile/profilePage";
	}

	@RequestMapping(value="/profile", params={"save"}, method=RequestMethod.POST)
	public String saveProfile(@Valid ProfileForm profileForm, BindingResult bindingResult){
		if (bindingResult.hasErrors()) {
			return "profile/profilePage";
		}
		System.out.println("save ok " + profileForm);
		return "redirect:/profile";
	}

	@ModelAttribute("dateFormat")
    public String localeFormat(Locale locale) {
        return USLocalDateFormatter.getPattern(locale);
    }

	@RequestMapping(value = "/profile", params = {"addTaste"})
	public String addRow(ProfileForm profileForm) {
			profileForm.getTastes().add(null);
			return "profile/profilePage";
	}

	@RequestMapping(value = "/profile", params = {"removeTaste"})
	public String removeRow(ProfileForm profileForm, HttpServletRequest req) {
			Integer rowId = Integer.valueOf(req.getParameter("removeTaste"));
			profileForm.getTastes().remove(rowId.intValue());
			return "profile/profilePage";
	}
}
