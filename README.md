## Modifyworld

### Permissions

This section describes all permissions which can be used in any permission plugin and how to use them. 

#### General

Permissions Node | Description
--- | ---
chat | Defines if user should able to chat
use-beds | User permission to sleep

#### Entities

Permissions Node | Arguments | Description | Notes
--- |: --- :| --- |: --- :
interact.* | enitites | Permission to right click actions on entities like shearing or milking cow | Also checks permission to use item in hand
target-by.* | entities | Permission to be followed by monsters or animals while holding proper food | -
tame.* | animals | Permission to tame animals such as horses or wolves | -
breed.* | animals | Checks if player can breed animals of given type | -
vehicle.enter.* | mountable animals | Permission to ride on animal | -
damage.deal.* | entities | Permission to deal damage to entity | Also checks permission to use item in hand

#### Items

Permissions Node | Arguments | Description | Notes
--- |: --- :| --- |: --- :
use.\*.on.\* | items and blocks/entities | Permission to use items specified in configuration on blocks or entities | This permissions is often used with other permissions to check if player can 
enchant.* | enchantable items | Permission to enchant items | -
throw.* | throwable items | Permission to throw items like eggs, snowballs, etc. | -
shoot.* | range weapon (bow, crossbow) | Permission to shoot a ranged weapon | -
eat.* | edible items | Permission to eat | -
smelt.* | smeltable items | Permission to smelt or cook item | -
craft.* | craftable items | Permission to craft items | -
drop.* | items | Permission to drop item on the ground | -
pickup.* | items | Permission to pickup items | -
hold.* | items | Permission to hold item in hand | -

#### Block related

Permissions Node | Arguments | Description | Notes
--- |: --- :| --- |: --- :
interact.* | interactable blocks (e.g. craftbenches, furnaces) | Permission to right click interactable blocks like anvils, brewing stand, etc. | Also checks if player can interact blocks with item in hand
bucket.empty.* | fluids | Permission to empty buccket full of fluid | Also checks if use can place fluid block (blocks.place) 
bucket.fill.* | fluids | Permission to fill bucket with specified fluid | -
blocks.break.* | blocks | Permission to break blocks | Also checks use of item in hand 
blocks.place.* | blocks | Permission to place blocks | -
hanging.break.* | hangings (e.g. paintings, itemframes) | Permission to break hangings | Also check use of item held in hand
hanging.place.* | hangings (e.g. paintings, itemframes) | Permission to place hangings | -

#### Transport

Permissions Node | Arguments | Description | Notes
--- |: --- :| --- |: --- :
transport.break.* | vehicles | Permission to destroy vehicles like boats or minecarts | -
transport.enter.* | vehicles and mountable animals | Permission to enter vehicles or ride animals | -
transport.collide.* | vehicles | Permissions to push vehicles | -

### <a name="arguments"></a>Arguments

This plugin uses [Minecraft IDs](https://minecraft.gamepedia.com/Java_Edition_data_values#Entities) written as lowercase strings without any underscores in permission config.

| Minecraft ID | Plugin equivalent | 
| --- | --- |
| snow_golem | snowgolem |
| light_blue_stained_glass_pane | lightbluestainedglasspane |
| shulker_bullet | shulkerbullet |