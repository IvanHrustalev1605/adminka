package com.example.adminkawebportal.view

import com.example.adminkawebportal.dto.ApplicationDto
import com.example.adminkawebportal.dto.CommentDto
import com.example.adminkawebportal.service.WebPortalService
import com.vaadin.flow.component.Unit
import com.vaadin.flow.component.button.Button
import com.vaadin.flow.component.dependency.CssImport
import com.vaadin.flow.component.dialog.Dialog
import com.vaadin.flow.component.grid.Grid
import com.vaadin.flow.component.notification.Notification
import com.vaadin.flow.component.orderedlayout.VerticalLayout
import com.vaadin.flow.component.textfield.TextField
import com.vaadin.flow.data.value.ValueChangeMode
import com.vaadin.flow.router.Route
import io.micrometer.common.util.StringUtils
import org.springframework.util.CollectionUtils

@Route("application")
@CssImport("theme/grid-styling.css")
class ApplicationView(private val webPortalService: WebPortalService) : VerticalLayout() {
    val grid = Grid(ApplicationDto::class.java)

    init {
        grid.setItems(webPortalService.getApplication())
        grid.isVisible = true
        grid.addClassName("styling")
        grid.setPartNameGenerator {
            return@setPartNameGenerator "low-rating"
        }
        val dialog = Dialog()
        dialog.isVisible = true
        dialog.setSizeUndefined()
        dialog.setWidth(700F, Unit.PIXELS)
        dialog.setHeight(700F, Unit.PIXELS)

        val plicanteIdSearch = TextField("Поиск по plicante_id")
        plicanteIdSearch.placeholder = "Введите PLICANTE_ID"
        plicanteIdSearch.isVisible = true
        plicanteIdSearch.valueChangeMode = ValueChangeMode.LAZY
        plicanteIdSearch.addValueChangeListener {
            searchByPlicanteId(it.value)
        }
        grid.addItemClickListener { item ->
            val applicationDto = item.item
            val commentGrid = Grid(CommentDto::class.java)
            val commentDtos =
                webPortalService.findAllCommnetsByApplicationPlicanteId(applicationDto.plicanteId)
            if (!CollectionUtils.isEmpty(commentDtos)) {
                commentGrid.setItems(commentDtos)
                dialog.add(commentGrid)
                dialog.isVisible = true
                dialog.open()
                commentGrid.addItemClickListener {
                    val commentDto = it.item
                    val answerToCommentDialog = Dialog()
                    dialog.add(answerToCommentDialog)
                    answerToCommentDialog.isVisible = true
                    answerToCommentDialog.open()
                    val answerToCommentTextField = TextField("Ответ на комментарий", "Введите ответ на комментарий")
                    answerToCommentTextField.valueChangeMode = ValueChangeMode.LAZY
                    answerToCommentTextField.addValueChangeListener {
                        webPortalService.setAnswerToComment(applicationDto.plicanteId, commentDto.plicanteId, it.value)
                        Notification.show("Ответ успешно отправлен", 5000, Notification.Position.TOP_CENTER)
                    }


                    answerToCommentDialog.add(answerToCommentTextField)
                    answerToCommentDialog.addDialogCloseActionListener {
                        answerToCommentDialog.removeAll()
                        answerToCommentDialog.close()
                    }
                }

            } else {
                dialog.add("Не найдено комментариев для заявки!")
                dialog.open()
            }
            dialog.addDialogCloseActionListener {
                dialog.removeAll()
                dialog.close()
                webPortalService.getApplication()
            }

        }
        val buttonBack = Button("На главную")
        buttonBack.addClickListener {
            ui.get().page.setLocation("/")
        }
        add(grid, plicanteIdSearch,dialog, buttonBack)



    }

    fun searchByPlicanteId(filterText: String?) {
        if (!StringUtils.isEmpty(filterText)) {
            grid.setItems(webPortalService.findByPlicanteId(filterText!!.toLong()))
        } else {
            grid.setItems(webPortalService.getApplication())
        }
    }
}