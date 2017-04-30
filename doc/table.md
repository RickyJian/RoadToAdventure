# **DataBase name : RoadToAdenture**
## **Table name : UserAccount**

ColumnName | ColumnType | Memo | FK
---| ---| --- |---
**UserID** | varchar(20) | Not Null |
UserName | varchar(50) | Not Null
UserPicture | Text | Null
UserRoleID | varchar(20) | Not Null | UserRoleID(UserRole)
EMail | varchar(50) | Not Null
Password | varchar(20) | Not Null
LastPassword | varchar(20) | Null
LastLoginTime | DateTime | Null
IsEnabled | char(1) | default N
IsVerification | char(1) | default N
CreateID | varchar(20) | Not Null | UserID (UserAccount)
CreateDate | DateTime | Not Null
ModifyID | varchar(20) | Null | UserID (UserAccount)
ModifyDate | DateTime | Null

## **Table name : UserRole**

ColumnName | ColumnType | Memo | FK
---| ---| --- |---
**UserRoleID** | varchar(20) | Not Null
UserRoleName | varchar(50) | Not Null
## **Table name : Group**

ColumnName | ColumnType | Memo | FK
---| ---| --- |---
**GroupID** | Int | IDENTITY
GroupName | varchar(50) | Not Null
GroupPicture |  Text | Not Null
CreateID | varchar(20) | Not Null | UserID (UserAccount)
CreateDate | DateTime | Not Null
ModifyID | varchar(20) | Null | UserID (UserAccount)
ModifyDate | DateTime | Null

## **Table name : GroupRole**

ColumnName | ColumnType | Memo | FK
---| ---| --- |---
**GroupRoleID** | varchar(20) | Not Null
GroupRoleName | varchar(50) | Not Null

## **Table name : UserInGroup**

ColumnName | ColumnType | Memo | FK
---| ---| --- |---
**UserID** | varchar(20) | Not Null | UserID (UserAccount)
**GroupID** | varchar(50) | Not Null | GroupID (Group)
GroupRoleID | varchar(20) |Not Null <br> default  | GroupRoleID(GroupRole)

## **Table name : GroupChat**

ColumnName | ColumnType | Memo | FK
---| ---| --- |---
**GroupChatID** | Int | IDENTITY
**GroupID** | Int | Not Null |  GroupID (Group)
**UserID** | varchar(20) | Not Null | UserID (UserAccount)
Content | Text | Not Null
CreateDate | DateTime | Not Null

## **Table name : GroupJourney**

ColumnName | ColumnType | Memo | FK
---| ---| --- |---
**GroupJourneyID** | Int | IDENTITY
**GroupID** | Int |	Not Null	 |  GroupID (Group)
GroupJourneyName | varchar(50) | Not Null
GroupJourneyContent | Text | Null
CreateID | varchar(20) | Not Null | UserID (UserAccount)
CreateDate | DateTime | Not Null
ModifyID | varchar(20) | Null | UserID (UserAccount)
ModifyDate | DateTime | Null

## **Table name : PersonalJourney**

ColumnName | ColumnType | Memo | FK
---| ---| --- |---
**PersonalJourneyID** | Int | IDENTITY
**UserID** | Int |	Not Null	 |  GroupID (Group)
PersonalJourneyName | varchar(50) | Not Null
PersonalJourneyContent | Text | Null
CreateID | varchar(20) | Not Null | UserID (UserAccount)
CreateDate | DateTime | Not Null
ModifyID | varchar(20) | Null | UserID (UserAccount)
ModifyDate | DateTime | Null

## **Table name : Chat**

ColumnName | ColumnType | Memo | FK
---| ---| --- |---
**ChatID** | Int | IDENTITY
**UserID** | varchar(20) | Not Null |  UserID (UserAccount)
**FriendID** | varchar(20) | Not Null |  UserID (UserAccount)
Content | Text | Not Null
CreateDate | DateTime | Not Null

## **Table name : Authority**

ColumnName | ColumnType | Memo | FK
---| ---| --- |---
**AuthorityID** | varchar(20) | Not Null
AuthorityName | varchar(50) | Not Null
Memo | varchar(50) | Null

## **Table name : UserRoleDefaultAuthority**

ColumnName | ColumnType | Memo | FK
---| ---| --- |---
**AuthorityID** | varchar(20) | Not Null | AuthorityID(Authority)
**UserRoleID** | varchar(20) | Not Null | UserRoleID(UserRole)

## **Table name : GroupRoleDefaultAuthority**

ColumnName | ColumnType | Memo | FK
---| ---| --- |---
**AuthorityID** | varchar(20) | Not Null | AuthorityID(Authority)
**GroupRoleID** | varchar(20) | Not Null | UserRoleID(UserRole)

## **Table name : GroupRolePersonalAuthority**

ColumnName | ColumnType | Memo | FK
---| ---| --- |---
**AuthorityID** | varchar(20) | Not Null | AuthorityID(Authority)
**GroupID** | Int | Not Null | GroupID(Group)
**UserID** | varchar(20) | Not Null | UserID(UserAccount)

## **Table name : CodeTable**

ColumnName | ColumnType | Memo | FK
---| ---| --- |---
**AttributeName** | varchar(50) | Not Null
**Code** | varchar(10) | Not Null
**Value** | varchar(20) | Not Null
fAttributeName| varchar(50) | Null
fCode| varchar(10) | Null

## **Table name : UserFriend**

ColumnName | ColumnType | Memo | FK
--- | --- | --- | ---
**UserID** | varchar(20) | Not Null | UserID(UserAccount)
**FriendID** | varchar(20) | Not Null | UserID(UserAccount)
CreateDate | DateTime | Not Null

## **Table name : PersonalJourneyDetail**

ColumnName | ColumnType | Memo | FK
--- | --- | --- | ---
**PersonalJourneyID** | Int | Not Null | PersonalJourneyID (PersonalJourney)
**UserID** | varchar(20) | Not Null | UserID(UserAccount)
**CreateDate** | DateTime | Not Null |
Latitude | varchar(30) | Not Null
Longitude | varchar(30) | Not Null

## **Table name : GroupJourneyDetail**

ColumnName | ColumnType | Memo | FK
--- | --- | --- | ---
**GroupJourneyID** | Int | Not Null | GroupJourneyID (GroupJourney)
**GroupID** | Int |	Not Null	 |  GroupID (Group)
**UserID** | varchar(20) | Not Null | UserID(UserAccount)
**CreateDate** | DateTime | Not Null |
Latitude | varchar(30) | Not Null
Longitude | varchar(30) | Not Null
=======
# **DataBase name : RoadToAdenture**
## **Table name : UserAccount**

ColumnName | ColumnType | Memo | FK
---| ---| --- |---
**UserID** | varchar(20) | Not Null |
UserName | varchar(50) | Not Null
UserPicture | Text | Null
UserRoleID | varchar(20) | Not Null | UserRoleID(UserRole)
Email | varchar(50) | Not Null
Password | varchar(32) | Not Null
LastPassword | varchar(20) | Null
LastLoginTime | DateTime | Null
IsEnabled | char(1) | default N
IsVerification | char(1) | default N
CreateID | varchar(20) | Not Null | UserID (UserAccount)
CreateDate | DateTime | Not Null
ModifyID | varchar(20) | Null | UserID (UserAccount)
ModifyDate | DateTime | Null

## **Table name : UserRole**

ColumnName | ColumnType | Memo | FK
---| ---| --- |---
**UserRoleID** | varchar(20) | Not Null
UserRoleName | varchar(50) | Not Null
## **Table name : Group**

ColumnName | ColumnType | Memo | FK
---| ---| --- |---
**GroupID** | Int | IDENTITY
GroupName | varchar(50) | Not Null
GroupPicture |  Text | Not Null
CreateID | varchar(20) | Not Null | UserID (UserAccount)
CreateDate | DateTime | Not Null
ModifyID | varchar(20) | Null | UserID (UserAccount)
ModifyDate | DateTime | Null

## **Table name : GroupRole**

ColumnName | ColumnType | Memo | FK
---| ---| --- |---
**GroupRoleID** | varchar(20) | Not Null
GroupRoleName | varchar(50) | Not Null

## **Table name : UserInGroup**

ColumnName | ColumnType | Memo | FK
---| ---| --- |---
**UserID** | varchar(20) | Not Null | UserID (UserAccount)
**GroupID** | int | Not Null | GroupID (Group)
GroupRoleID | varchar(20) |Not Null <br> default  | GroupRoleID(GroupRole)

## **Table name : GroupChat**

ColumnName | ColumnType | Memo | FK
---| ---| --- |---
**GroupChatID** | Int | IDENTITY
**GroupID** | Int | Not Null |  GroupID (Group)
**UserID** | varchar(20) | Not Null | UserID (UserAccount)
Content | Text | Not Null
CreateDate | DateTime | Not Null

## **Table name : GroupJourney**

ColumnName | ColumnType | Memo | FK
---| ---| --- |---
**GroupJourneyID** | Int | IDENTITY
**GroupID** | Int |	Not Null	 |  GroupID (Group)
GroupJourneyName | varchar(50) | Not Null
GroupJourneyContent | Text | Null
CreateID | varchar(20) | Not Null | UserID (UserAccount)
CreateDate | DateTime | Not Null
ModifyID | varchar(20) | Null | UserID (UserAccount)
ModifyDate | DateTime | Null

## **Table name : PersonalJourney**

ColumnName | ColumnType | Memo | FK
---| ---| --- |---
**PersonalJourneyID** | Int | IDENTITY
**UserID** | Int |	Not Null	 |  GroupID (Group)
PersonalJourneyName | varchar(50) | Not Null
PersonalJourneyContent | Text | Null
CreateID | varchar(20) | Not Null | UserID (UserAccount)
CreateDate | DateTime | Not Null
ModifyID | varchar(20) | Null | UserID (UserAccount)
ModifyDate | DateTime | Null

## **Table name : Chat**

ColumnName | ColumnType | Memo | FK
---| ---| --- |---
**ChatID** | Int | IDENTITY
**UserID** | varchar(20) | Not Null |  UserID (UserAccount)
Content | Text | Not Null
CreateDate | DateTime | Not Null

## **Table name : Authority**

ColumnName | ColumnType | Memo | FK
---| ---| --- |---
**AuthorityID** | varchar(20) | Not Null
AuthorityName | varchar(50) | Not Null
Memo | varchar(50) | Null

## **Table name : UserRoleDefaultAuthority**

ColumnName | ColumnType | Memo | FK
---| ---| --- |---
**AuthorityID** | varchar(20) | Not Null | AuthorityID(Authority)
**UserRoleID** | varchar(20) | Not Null | UserRoleID(UserRole)

## **Table name : GroupRoleDefaultAuthority**

ColumnName | ColumnType | Memo | FK
---| ---| --- |---
**AuthorityID** | varchar(20) | Not Null | AuthorityID(Authority)
**GroupRoleID** | varchar(20) | Not Null | UserRoleID(UserRole)

## **Table name : GroupRolePersonalAuthority**

ColumnName | ColumnType | Memo | FK
---| ---| --- |---
**AuthorityID** | varchar(20) | Not Null | AuthorityID(Authority)
**GroupID** | Int | Not Null | GroupID(Group)
**UserID** | varchar(20) | Not Null | UserID(UserAccount)

## **Table name : CodeTable**

ColumnName | ColumnType | Memo | FK
---| ---| --- |---
**AttributeName** | varchar(50) | Not Null
**Code** | varchar(10) | Not Null
**Value** | varchar(20) | Not Null
FAttributeName| varchar(50) | Null | CodeTable(AttributeName)
FCode| varchar(10) | Null | CodeTable(Code)

## **Table name : UserFriend**

ColumnName | ColumnType | Memo | FK
--- | --- | --- | ---
**UserID** | varchar(20) | Not Null | UserID(UserAccount)
**FriendID** | varchar(20) | Not Null | UserID(UserAccount)
CreateDate | DateTime | Not Null

## **Table name : PersonalJourneyDetail**

ColumnName | ColumnType | Memo | FK
--- | --- | --- | ---
**PersonalJourneyID** | Int | Not Null | PersonalJourneyID (PersonalJourney)
**UserID** | varchar(20) | Not Null | UserID(UserAccount)
**CreateDate** | DateTime | Not Null |
Latitude | varchar(30) | Not Null
Longitude | varchar(30) | Not Null

## **Table name : GroupJourneyDetail**

ColumnName | ColumnType | Memo | FK
--- | --- | --- | ---
**GroupJourneyID** | Int | Not Null | GroupJourneyID (GroupJourney)
**GroupID** | Int |	Not Null	 |  GroupID (GroupJourneyID)
Latitude | varchar(30) | Not Null
Longitude | varchar(30) | Not Null
CreateID | varchar(20) | Not Null | UserID (UserAccount)
**CreateDate** | DateTime | Not Null
ModifyID | varchar(20) | Null | UserID (UserAccount)
ModifyDate | DateTime | Null

