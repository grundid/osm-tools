/**
 * This file is part of Amenity Editor for OSM.
 * Copyright (c) 2001 by Adrian Stabiszewski, as@grundid.de
 *
 * Amenity Editor is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Amenity Editor is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with Amenity Editor.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.osmtools.oauth;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.osmtools.api.OsmOperations;
import org.osmtools.api.UserOperations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class OauthRequestController {

	@Autowired
	private OauthService oauthService;
	@Autowired
	private OsmOperations osmOperations;

	@RequestMapping("/oauthRequest")
	public void oauthRequest(HttpServletResponse response) throws IOException {
		String url = oauthService.getRequestTokenUrl(response);
		response.sendRedirect(url);
	}

	@RequestMapping("/oauthResponse")
	public String oauthResponse(
			@SuppressWarnings("unused") @RequestParam(value = "oauth_token", defaultValue = "") String oAuthToken,
			@RequestParam(value = "oauth_verifier", defaultValue = "") String oAuthVerifier,
			RedirectAttributes attributes) {
		OauthTokens oauthTokens = oauthService.retrieveAccessToken(oAuthVerifier);

		UserOperations userOperations = osmOperations.userOperations(oauthTokens);

		try {
			attributes.addFlashAttribute("osmUser", userOperations.getForUser());
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		attributes.addFlashAttribute("oauthTokens", oauthTokens);
		return "redirect:index";
	}
}
