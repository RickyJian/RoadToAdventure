# **DataBase name : RoadToAdenture**
## **Table name : UserAccount**

ColumnName | ColumnType | Memo | FK
---| ---| --- |---
**UserID** | varchar(50) | 帳號 |
UserName | varchar(50) | 名稱
UserPicture | Text | 大頭貼
EMail | varchar(50) | 信箱
Password | varchar(20) | 密碼
VerificationCode | varchar(10) | 驗證碼
Weight | int | 體重
SignInOrigin | varchar(20) | 註冊來源(facebook, google, roadtoadventure)
CreateDate | DateTime | 註冊日期
ModifyDate | DateTime | 修改

## **Table name : UserRole**
ColumnName | ColumnType | Memo | FK
---| ---| --- |---
**UserRoleID** | varchar(20) | 角色代號
UserRoleName | varchar(50) | 名稱

## **Table name : Group**
ColumnName | ColumnType | Memo | FK
---| ---| --- |---
**GroupID** | Int | 群組編號
GroupName | varchar(50) | 名稱
GroupPicture |  Text | 圖片
CreateID | varchar(50) | 建立人ID | UserID (UserAccount)
CreateDate | DateTime | 建立日期
ModifyID | varchar(50) | 修改人ID | UserID (UserAccount)
ModifyDate | DateTime | 修改日期

## **Table name : GroupRole**

ColumnName | ColumnType | Memo | FK
---| ---| --- |---
**GroupRoleID** | varchar(20) | 群組角色代號
GroupRoleName | varchar(50) | 名稱

## **Table name : UserInGroup**

ColumnName | ColumnType | Memo | FK
---| ---| --- |---
**UserID** | varchar(20) | 成員ID | UserID (UserAccount)
**GroupID** | varchar(50) | 群組編號 | GroupID (Group)
GroupRoleID | varchar(20) | 群組角色代號  | GroupRoleID(GroupRole)

## **Table name : GroupChat**

ColumnName | ColumnType | Memo | FK
---| ---| --- |---
**GroupChatID** | Int | 群組聊天編號
GroupID | Int | 群組編號 |  GroupID (Group)
UserID | varchar(20) | 成員ID | UserID (UserAccount)
Content | Text | 內容
CreateDate | DateTime | 建立時間

## **Table name : PersonalJourney**

ColumnName | ColumnType | Memo | FK
---| ---| --- |---
**PersonalJourneyID** | Int | 個人旅程編號
UserID | varchar(20) |  |  UserID (UserAccount)
PersonalJourneyName | varchar(50) | 名稱
PersonalJourneyContent | Text | 內容
Points | Text | 路程編碼
Status | char(1) | 狀態 0(規劃中)/1(進行中)/2(結束)
IsOpen | char(1) | 是否公開
StartTime | DateTime | 開始時間
EndTime | DateTime | 結束時間
CreateID | varchar(50) | 建立人ID | UserID (UserAccount)
CreateDate | DateTime | 建立日期
ModifyID | varchar(50) | 修改人ID | UserID (UserAccount)
ModifyDate | DateTime | 修改日期

## **Table name : PersonalJourneyPicture**
ColumnName | ColumnType | Memo | FK
---| ---| --- |---
**PersonalJourneyPictureID** | Int | 個人旅程圖片編號
PersonalJourneyID | Int | 個人旅程編號 |  PersonalJourneyID (PersonalJourney)
PicturePath | varchar(200) | 圖片路徑
CreateID | varchar(50) | 建立人ID | UserID (UserAccount)
CreateDate | DateTime | 建立日期

## **Table name : PersonalJourneyComment**
ColumnName | ColumnType | Memo | FK
---| ---| --- |---
**PersonalJourneyCommentID** | Int | 個人旅程留言編號
PersonalJourneyID | Int | 個人旅程編號 |  PersonalJourneyID (PersonalJourney)
Comment | Text | 留言
CreateID | varchar(50) | 建立人ID | UserID (UserAccount)
CreateDate | DateTime | 建立日期

## **Table name : Chat**
ColumnName | ColumnType | Memo | FK
---| ---| --- |---
**ChatID** | Int | 聊天編號
UserID | varchar(20) | 發送方ID |  UserID (UserAccount)
FriendID | varchar(20) | 接收方ID |  UserID (UserAccount)
Content | Text | 內容
CreateDate | DateTime | 建立日期

## **Table name : UserFriend**
ColumnName | ColumnType | Memo | FK
--- | --- | --- | ---
**UserID** | varchar(20) | 使用者ID | UserID(UserAccount)
**FriendID** | varchar(20) | 好友ID | UserID(UserAccount)
CreateDate | DateTime | 建立日期

## **Table name : PersonalJourneyDetail**
ColumnName | ColumnType | Memo | FK
--- | --- | --- | ---
**PersonalJourneyID** | Int | 個人旅程路徑編號 | PersonalJourneyID (PersonalJourney)
Latitude | varchar(30) | 緯度
Longitude | varchar(30) | 經度
UserID | varchar(20) | 建立人 | UserID(UserAccount)
CreateDate | DateTime | 建立日期 

## **Table name : GroupJourney**
ColumnName | ColumnType | Memo | FK
---| ---| --- |---
**GroupJourneyID** | Int | 群組旅程編號
GroupID | Int | 群組編號 |  GroupID (Group)
GroupJourneyName | varchar(50) | 名稱
GroupJourneyContent | Text | 內容
Points | Text | 路程編碼
IsOpen | char(1) | 是否公開
CreateID | varchar(50) | 建立人 | UserID (UserAccount)
CreateDate | DateTime | 建立日期
ModifyID | varchar(50) | 修改人 | UserID (UserAccount)
ModifyDate | DateTime | 修改日期

## **Table name : MemberInGroupJourney**
ColumnName | ColumnType | Memo | FK
---| ---| --- |---
**MemberInGroupJourneyID** | Int | 成員in群組旅程編號
GroupJourneyID | Int | 群組旅程編號 |  GroupJourneyID (GroupJourney)
UserID | varchar(50) | 成員ID (UserAccount)
StartTime | DateTime | 開始時間
EndTime | DateTime | 結束時間

## **Table name : GroupJourneyPicture**
ColumnName | ColumnType | Memo | FK
--- | --- | --- | ---
**GroupJourneyPictureID** | Int | 群組旅程圖片編號
GroupJourneyID | Int | 群組旅程編號 | GroupJourneyID (GroupJourney)
PicturePath | varchar(200) | 圖片路徑
CreateDate | DateTime | 建立日期

## **Table name : GroupJourneyDetail**
ColumnName | ColumnType | Memo | FK
--- | --- | --- | ---
**GroupJourneyDetailID** | Int | 群組旅程路徑編號
GroupJourneyID | Int | 群組旅程編號 | GroupJourneyID (GroupJourney)
Latitude | varchar(30) | 緯度
Longitude | varchar(30) | 經度
UserID | varchar(20) | 成員ID | UserID(UserAccount)
CreateDate | DateTime | 建立日期

## **Table name : GroupJoin**
ColumnName | ColumnType | Memo | FK
--- | --- | --- | ---
**GroupJoinID** | Int | 揪團編號
GroupJourneyID | Int | 群組旅程編號 | GroupJourneyID (GroupJourney)
Content | varchar(200) | 內容
ExpireDate | DateTime | 到期時間
Enable | varchar(1) | 是否啟用
CreateID | varchar(50) | 建立人ID | UserID (UserAccount)
CreateDate | DateTime | 建立日期
ModifyID | varchar(50) | 修改人ID | UserID (UserAccount)
ModifyDate | DateTime | 修改日期

## **Table name : GroupJoinUser**
ColumnName | ColumnType | Memo | FK
--- | --- | --- | ---
**GroupJoinUserID** | Int | 使用者in揪團ID 
GroupJoinID | Int | 揪團編號 | GroupJoinID (GroupJoin)
CreateID | varchar(50) | 建立人ID | UserID (UserAccount)
CreateDate | DateTime | 建立日期 