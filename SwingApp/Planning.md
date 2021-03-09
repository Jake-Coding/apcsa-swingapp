# Idea- User-created Lists
## Pull up list
[button: BACK]
|Task Name  | Description (editable)   |  Checkbox (finished)| Buttons
|---|---|---|---|
|Eat  | consume | Y | Delete
|Walk Home  |I will walk home  |N |  Delete

[Save button] [Delete All]

## Default view
[input filename] [button: OPEN]


## Components
### backend:
- BigList (ListItem[])
  - delete, add, save to file, read from Scanner, edit individual elements
  - Getters/Setters, parse, toComponent, sort
- ListItem implements Comparable\<ListItem>
    - Name, Description, IsDone
    - Getters/setters, toComponent, compareTo (sort by name)

### frontend:
- App 
    - default view toggle (back button) 
    - main method (build app)
    - On default view
      - getFileFromInput
    - On list view
      - saveCurrentList
      - editListItem