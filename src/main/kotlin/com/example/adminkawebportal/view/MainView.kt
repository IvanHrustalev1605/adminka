package com.example.adminkawebportal.view

import com.example.adminkawebportal.service.WebPortalService
import com.vaadin.flow.component.Unit
import com.vaadin.flow.component.button.Button
import com.vaadin.flow.component.notification.Notification
import com.vaadin.flow.component.orderedlayout.BoxSizing
import com.vaadin.flow.component.orderedlayout.FlexComponent
import com.vaadin.flow.component.orderedlayout.HorizontalLayout
import com.vaadin.flow.component.orderedlayout.VerticalLayout
import com.vaadin.flow.component.textfield.TextArea
import com.vaadin.flow.component.textfield.TextField
import com.vaadin.flow.router.Route

@Route("/")
class MainView(private val webPortalService: WebPortalService) : VerticalLayout() {

    init {

        val horizontalLayout = HorizontalLayout()
        horizontalLayout.setVerticalComponentAlignment(FlexComponent.Alignment.CENTER)
        horizontalLayout.boxSizing = BoxSizing.BORDER_BOX
        val button1 = Button("Все заявки")
        button1.addClickListener {
            ui.get().page.setLocation("/application")
        }
        val button2 = Button("Кнопка 2")
        val button3 = Button("Кнопка 3")
        horizontalLayout.add(button1, button2, button3)

        val textArea = TextArea()
        textArea.setWidthFull()
        textArea.getStyle().set("overflow", "hidden")
        textArea.value = "Тут будем отвечать на комментарий. Кликни на меня"
        val testTextArea = TextArea()
        testTextArea.isVisible = false
        val textField = TextField()
        textField.isVisible = false
        val sendCommentButton = Button()
        sendCommentButton.text = "Отправить ответ на комментарий"
        sendCommentButton.isVisible = false
        textArea.addFocusListener {
            textField.isVisible = true
            textField.label = "Введите текст ответа на комментарий"
            textField.setWidth(390F, Unit.PIXELS)
            textField.addValueChangeListener {event ->
                if (event.value != null) {
                    sendCommentButton.isVisible = true
                    sendCommentButton.addClickListener {
                        testTextArea.isVisible = true
                        webPortalService.gelList().forEach {
                            testTextArea.value = it.name
                            testTextArea.value = it.plicanteId.toString()
                        }
                    }
                } else {
                    Notification.show("Введите текст! Ленивая ты сука!", 5000, Notification.Position.TOP_CENTER)
                }
            }
        }
        add(horizontalLayout, textArea, textField, sendCommentButton, testTextArea)
    }
}