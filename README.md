INT20h

**ТЕСТОВЕ ЗАВДАННЯ**

Команда: **looploop**

Склад команди:

Альона Калитенко [https://github.com/gr-alia](https://github.com/gr-alia)

Валерій Демчик [https://github.com/KirinTor](https://github.com/KirinTor)

Віталій Тизунь [https://github.com/srdnx](https://github.com/srdnx)

Спільний репозитарій: [https://github.com/gr-alia/test-int20h](https://github.com/gr-alia/test-int20h)

**ЗАВДАННЯ**

Створити мобільний додаток, який виконуватиме наступні функції:

1. За показниками курсу USD/BTC, отриманими з трьох криптовалютних обмінників, визначатиме чи ефективно придбати криптовалюту на одному з них, вилучити її, та продати на якомусь з двох інших.
2. За статистичними показниками курсу USD/BTC, взятими за певний період часу, виводить у вигляді лінійного графіку динаміку зміни вартості одного біткойну на цьому проміжку часу.

**ПРОГРАМНІ ЗАСОБИ, ВИКОРИСТАНІ ДЛЯ ВИКОНАННЯ ЗАВДАННЯ**

Цільовою платформою виконання було обрано **Android**.

Обмін даними з API онлайн сервісів ведеться засобами JSON. Переліки сервісів, API яких використовуються в різних частинах програми, наведено в відповідних розділах. Також, окрім стандартних засобів платформи Android, було підключено наступні сторонні бібліотеки:

1. **Retrofit** – для роботи з запитами до API
2. **MPAndroidChart** – для побудови Android графіку курсу біткойну в частині 2

Послідовність дій при побудові проекту наступна:

1. Встановити JDK, JRE, Android Studio, Android SDK, Genymotion.
2. Створити git-репозитарій проекту, наприклад за допомогою сервісу
3. Створити Android проект додатку в даному репозитарії.
4. Здійснити опис поведінкової логіки додатку, в разі необхідності підключивши сторонні бібліотеки.
5. Здійснити опис користувацького інтерфейсу додатку, в разі необхідності підключивши сторонні бібліотеки.
6. Здійснити поєднання роботи логіки та інтерфейсу додатку.
7. Провести тестування розробленого продукту.





**ЧАСТИНА**  **I**

**Для першої частини завдання дані взято з наступних криптовалютних обмінників:**

| **Посилання на обмінник:** | **Посилання на використаний запит**  **API:** |
| --- | --- |
| [**https://yobit.net/ru/**](https://yobit.net/ru/) | [**https://yobit.io/api/3/depth/btc\_usd?limit=100**](https://yobit.io/api/3/depth/btc_usd?limit=100) |
| [**https://www.kraken.com/**](https://www.kraken.com/) | [**https://api.kraken.com/0/public/Depth?pair=XBTUSD**](https://api.kraken.com/0/public/Depth?pair=XBTUSD) |
| [**https://exmo.com/**](https://exmo.com/) | [**https://api.exmo.com/v1/order\_book/?pair=BTC\_USD**](https://api.exmo.com/v1/order_book/?pair=BTC_USD) |

**Дані про комісії взято за наступними посиланнями:**

| **Обмінник:** | **Дані про комісію:** |
| --- | --- |
| **Yobit** | [**https://yobit.net/ru/fees/**](https://yobit.net/ru/fees/) Комісія за введення коштів відсутня; Комісія за виведення криптовалюти 0.0005(0.05%); Комісія за виведення коштів відсутня, на прикладі Visa/MasterCard сервісів |
| **Kraken** | [**https://www.kraken.com/help/fees**](https://www.kraken.com/help/fees) [**https://support.kraken.com/hc/en-us/articles/201893608-What-are-the-withdrawal-fees**](https://support.kraken.com/hc/en-us/articles/201893608-What-are-the-withdrawal-fees) Комісія за введення коштів – 10$; Комісія за відкриття позиції - 0.00005 (0.005%); Комісія за виведення криптовалюти – 0.001(0.1%); Комісія за виведення коштів - 60$; |
| **Exmo** | [**https://exmo.com/ru/docs/fee**](https://exmo.com/ru/docs/fee) Комісія за введення коштів відсутня, на прикладі Visa/MasterCard сервісів; Комісія за угоду - 0.002 (0.2%); Комісія за виведення криптовалюти – 0.001 (0.1%); Комісія за виведення коштів - 0.03 (3%) + 7.5USD |

**Логіка роботи алгоритму пошуку кращого рішення в цій частині наступна:**

1. Визначаємо, скільки грошей ми витратимо на покупку біткойну на кожному з сервісів. Для цього отримуємо **OrdersBook** фактичних пропозицій продажу біткойнівкожного сервісу, ведемо пошук найкращої пропозиції ( **мінімальна вартість** в USD). Також на цьому кроці отримані суми **збільшуються** відповідно до комісійних показників відповідних обмінників.
2. Обчислюємо, яка фактична кількість біткойнів залишиться в нас після врахування комісійних зборів внаслідок виведення їх із сервісу, на якому вони були придбані.
3. Визначаємо, яку кількість грошей ми матимемо внаслідок продажу фактичної кількості біткойнів на двох інших обмінниках. Для цього отрмуємо **OrdersBook** фактичних пропозицій купівлі біткойнівкожного сервісу, ведемо пошук найкращої пропозиції (максимальна вартість в USD). **Зменшуємо** отриману суму відповідно до показників комісії на відповідному сервісі.
4. Знаходимо найкращу пару обмінників, на першому з яких можна найдешевше придбати біткойн, а на другому найдорожче продати те, що лишиться від біткойну після його виведення з сервісу, на якому він був придбаний. Також на цьому кроці виводиться кількість грошей, яку ми матимемо в результаті, як **різниця між виведеними з другого обміннику коштами та затраченими на першому обміннику**.
5. Дані, отримані на попередньому кроці, виводяться користувачеві.



**ЧАСТИНА**  **II**

Для другої частини завдання дані взято з офіційного сайту **https://blockchain.info** , посилання на відповідний запит API:

[**https://blockchain.info/charts/market-price?format=json**](https://blockchain.info/charts/market-price?format=json)

Дані, отримані від даного сервісу, приведено до формату, зручного до розуміння користувачем, шляхом форматування показнику дати та часу, та подано у вигляді лінійного графіку. На цьому графіку вісь ОХ – час, вісь ОY – показник вартості 1 біткойну в доларах США в відповідний час. Дані взято за певний обмежений період, з періодичністю зміни в 1 календарний день.





