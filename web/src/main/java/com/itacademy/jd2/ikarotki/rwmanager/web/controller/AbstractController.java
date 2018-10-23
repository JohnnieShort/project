package com.itacademy.jd2.ikarotki.rwmanager.web.controller;

import javax.servlet.http.HttpServletRequest;

import com.itacademy.jd2.ikarotki.rwmanager.dao.api.filter.AbstractFilter;
import com.itacademy.jd2.ikarotki.rwmanager.web.dto.list.GridStateDTO;
import com.itacademy.jd2.ikarotki.rwmanager.web.dto.list.SortDTO;

public abstract class AbstractController<DTO> {
	protected GridStateDTO getListDTO(final HttpServletRequest req) {
		final String sessionModelName = getClass().getSimpleName() + "_LIST_MODEL";

		GridStateDTO listModel = (GridStateDTO) req.getSession().getAttribute(sessionModelName);
		if (listModel == null) {
			listModel = new GridStateDTO();
			req.getSession().setAttribute(sessionModelName, listModel);
		}
		req.setAttribute(GridStateDTO.GRID_STATE_SESSION_KEY, listModel);
		return listModel;
	}

	protected void prepareFilter(GridStateDTO listDTO, AbstractFilter filter) {
		filter.setLimit(listDTO.getItemsPerPage());
        int offset = listDTO.getItemsPerPage() * (listDTO.getPage() - 1);
        filter.setOffset(listDTO.getTotalCount() < offset ? 0 : offset);
        
		final SortDTO sortModel = listDTO.getSort();
		if (sortModel != null) {
			filter.setSortColumn(sortModel.getColumn());
			filter.setSortOrder(sortModel.isAscending());
		}
	}

}
