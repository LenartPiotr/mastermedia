# -*- coding: utf-8 -*-

################################################################################
## Form generated from reading UI file 'form.ui'
##
## Created by: Qt User Interface Compiler version 6.4.3
##
## WARNING! All changes made in this file will be lost when recompiling UI file!
################################################################################

from PySide6.QtCore import (QCoreApplication, QDate, QDateTime, QLocale,
    QMetaObject, QObject, QPoint, QRect,
    QSize, QTime, QUrl, Qt)
from PySide6.QtGui import (QBrush, QColor, QConicalGradient, QCursor,
    QFont, QFontDatabase, QGradient, QIcon,
    QImage, QKeySequence, QLinearGradient, QPainter,
    QPalette, QPixmap, QRadialGradient, QTransform)
from PySide6.QtWidgets import (QApplication, QFrame, QHBoxLayout, QLabel,
    QMainWindow, QPushButton, QSizePolicy, QVBoxLayout,
    QWidget)
import rc_resources

class Ui_MainWindow(object):
    def setupUi(self, MainWindow):
        if not MainWindow.objectName():
            MainWindow.setObjectName(u"MainWindow")
        MainWindow.resize(1280, 729)
        font = QFont()
        font.setFamilies([u"Parchment"])
        MainWindow.setFont(font)
        MainWindow.setStyleSheet(u"#menu{\n"
"	background: #29292a;\n"
"\n"
"}\n"
"\n"
"#main{\n"
"	background: #545454;\n"
"}\n"
"\n"
"#frame{\n"
"	background: #29292a;\n"
"}\n"
"\n"
"#phone_container{\n"
"	background: #29292a;\n"
"	border-radius: 20px;\n"
"}\n"
"\n"
"#home, #search, #albums, #settings{\n"
"	color: #fff;\n"
"	border-radius: 10px;\n"
"	height: 32px;\n"
"	font-size: 24px;\n"
"}\n"
"\n"
"#name{\n"
"	font-size: 32px;\n"
"	text-align: center;\n"
"	color: #fff;\n"
"}\n"
"\n"
"#label_1{\n"
"	font-size: 48px;\n"
"	color: #fff;\n"
"}\n"
"\n"
"#label_2{\n"
"	font-size: 24px;\n"
"	color: #fff;\n"
"}\n"
"\n"
"#reload{\n"
"	font-size: 16px;\n"
"	color: #25abff;\n"
"	background: #545454;\n"
"	border-radius: 10px;\n"
"}\n"
"\n"
"#single_phone{\n"
"	background: #545454;\n"
"	border-radius: 20px;\n"
"}\n"
"\n"
"#phone_label{\n"
"	color: #ffffff;\n"
"	font-size: 16px;\n"
"}\n"
"\n"
"#connect{\n"
"	color: #ffffff;\n"
"	background: #25abff;\n"
"	border-radius: 10px;\n"
"}\n"
"\n"
"\n"
"#phone_icon{\n"
"	background: #545454;\n"
"	border-radius: 20"
                        "px;\n"
"}")
        MainWindow.setIconSize(QSize(50, 50))
        self.centralwidget = QWidget(MainWindow)
        self.centralwidget.setObjectName(u"centralwidget")
        self.horizontalLayout = QHBoxLayout(self.centralwidget)
        self.horizontalLayout.setObjectName(u"horizontalLayout")
        self.mainBody = QWidget(self.centralwidget)
        self.mainBody.setObjectName(u"mainBody")
        self.menu = QWidget(self.mainBody)
        self.menu.setObjectName(u"menu")
        self.menu.setGeometry(QRect(-10, -20, 331, 731))
        self.frame = QFrame(self.menu)
        self.frame.setObjectName(u"frame")
        self.frame.setGeometry(QRect(10, 170, 321, 381))
        font1 = QFont()
        font1.setStyleStrategy(QFont.NoAntialias)
        self.frame.setFont(font1)
        self.frame.setStyleSheet(u"")
        self.frame.setFrameShape(QFrame.StyledPanel)
        self.frame.setFrameShadow(QFrame.Raised)
        self.verticalLayout_3 = QVBoxLayout(self.frame)
        self.verticalLayout_3.setObjectName(u"verticalLayout_3")
        self.home = QPushButton(self.frame)
        self.home.setObjectName(u"home")
        palette = QPalette()
        brush = QBrush(QColor(255, 255, 255, 255))
        brush.setStyle(Qt.SolidPattern)
        palette.setBrush(QPalette.Active, QPalette.WindowText, brush)
        palette.setBrush(QPalette.Active, QPalette.Text, brush)
        palette.setBrush(QPalette.Active, QPalette.ButtonText, brush)
        palette.setBrush(QPalette.Inactive, QPalette.WindowText, brush)
        palette.setBrush(QPalette.Inactive, QPalette.Text, brush)
        palette.setBrush(QPalette.Inactive, QPalette.ButtonText, brush)
        palette.setBrush(QPalette.Disabled, QPalette.WindowText, brush)
        palette.setBrush(QPalette.Disabled, QPalette.Text, brush)
        palette.setBrush(QPalette.Disabled, QPalette.ButtonText, brush)
        self.home.setPalette(palette)
        icon = QIcon()
        icon.addFile(u":/icons/folder.svg", QSize(), QIcon.Normal, QIcon.Off)
        self.home.setIcon(icon)
        self.home.setIconSize(QSize(24, 24))

        self.verticalLayout_3.addWidget(self.home)

        self.search = QPushButton(self.frame)
        self.search.setObjectName(u"search")
        icon1 = QIcon()
        icon1.addFile(u":/icons/search.svg", QSize(), QIcon.Normal, QIcon.Off)
        self.search.setIcon(icon1)
        self.search.setIconSize(QSize(24, 24))

        self.verticalLayout_3.addWidget(self.search)

        self.albums = QPushButton(self.frame)
        self.albums.setObjectName(u"albums")
        icon2 = QIcon()
        icon2.addFile(u":/icons/albums.svg", QSize(), QIcon.Normal, QIcon.Off)
        self.albums.setIcon(icon2)
        self.albums.setIconSize(QSize(24, 24))

        self.verticalLayout_3.addWidget(self.albums)

        self.settings = QPushButton(self.frame)
        self.settings.setObjectName(u"settings")
        icon3 = QIcon()
        icon3.addFile(u":/icons/settings.svg", QSize(), QIcon.Normal, QIcon.Off)
        self.settings.setIcon(icon3)
        self.settings.setIconSize(QSize(24, 24))

        self.verticalLayout_3.addWidget(self.settings)

        self.name = QLabel(self.menu)
        self.name.setObjectName(u"name")
        self.name.setGeometry(QRect(10, 50, 321, 51))
        font2 = QFont()
        self.name.setFont(font2)
        self.main = QWidget(self.mainBody)
        self.main.setObjectName(u"main")
        self.main.setGeometry(QRect(320, 0, 941, 721))
        self.label_1 = QLabel(self.main)
        self.label_1.setObjectName(u"label_1")
        self.label_1.setGeometry(QRect(70, 40, 161, 41))
        self.label_2 = QLabel(self.main)
        self.label_2.setObjectName(u"label_2")
        self.label_2.setGeometry(QRect(70, 130, 411, 31))
        self.line = QFrame(self.main)
        self.line.setObjectName(u"line")
        self.line.setGeometry(QRect(70, 170, 771, 20))
        self.line.setFrameShape(QFrame.HLine)
        self.line.setFrameShadow(QFrame.Sunken)
        self.phone_container = QFrame(self.main)
        self.phone_container.setObjectName(u"phone_container")
        self.phone_container.setGeometry(QRect(69, 249, 781, 401))
        self.single_phone = QWidget(self.phone_container)
        self.single_phone.setObjectName(u"single_phone")
        self.single_phone.setGeometry(QRect(59, 49, 211, 311))
        self.phone_label = QLabel(self.single_phone)
        self.phone_label.setObjectName(u"phone_label")
        self.phone_label.setGeometry(QRect(0, 240, 211, 21))
        self.connect = QPushButton(self.single_phone)
        self.connect.setObjectName(u"connect")
        self.connect.setGeometry(QRect(60, 270, 101, 23))
        font3 = QFont()
        font3.setBold(True)
        self.connect.setFont(font3)
        self.phone_icon = QPushButton(self.single_phone)
        self.phone_icon.setObjectName(u"phone_icon")
        self.phone_icon.setEnabled(False)
        self.phone_icon.setGeometry(QRect(0, 30, 211, 191))
        icon4 = QIcon()
        icon4.addFile(u":/icons/phone.svg", QSize(), QIcon.Normal, QIcon.Off)
        self.phone_icon.setIcon(icon4)
        self.phone_icon.setIconSize(QSize(200, 200))
        self.reload = QPushButton(self.main)
        self.reload.setObjectName(u"reload")
        self.reload.setGeometry(QRect(770, 190, 71, 25))
        self.reload.setFont(font2)

        self.horizontalLayout.addWidget(self.mainBody)

        MainWindow.setCentralWidget(self.centralwidget)

        self.retranslateUi(MainWindow)

        QMetaObject.connectSlotsByName(MainWindow)
    # setupUi

    def retranslateUi(self, MainWindow):
        MainWindow.setWindowTitle(QCoreApplication.translate("MainWindow", u"MainWindow", None))
        self.home.setText(QCoreApplication.translate("MainWindow", u"Home", None))
        self.search.setText(QCoreApplication.translate("MainWindow", u"Search", None))
        self.albums.setText(QCoreApplication.translate("MainWindow", u"Albums", None))
        self.settings.setText(QCoreApplication.translate("MainWindow", u"Settings", None))
        self.name.setText(QCoreApplication.translate("MainWindow", u"<html><head/><body><p align=\"center\"><span style=\" font-size:19pt; font-family:'Montserrat\">MediaMaster</span></p></body></html>", None))
        self.label_1.setText(QCoreApplication.translate("MainWindow", u"Phones", None))
        self.label_2.setText(QCoreApplication.translate("MainWindow", u"Connect your phone to your computer", None))
        self.phone_label.setText(QCoreApplication.translate("MainWindow", u"<html><head/><body><p align=\"center\"><span style=\" font-size:10pt;\">iPhone 13</span></p></body></html>", None))
        self.connect.setText(QCoreApplication.translate("MainWindow", u"Connect", None))
        self.phone_icon.setText("")
        self.reload.setText(QCoreApplication.translate("MainWindow", u"Reload", None))
    # retranslateUi

