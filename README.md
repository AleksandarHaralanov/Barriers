# BarrierBlock
Allows for placement of invisible pass-through barrier blocks.

Can be used for multiple purposes, including:
- Stopping water and/or lava flow.
- Levitating signs.
- And more, <i>if you're creative</i>.

<b>Download latest release [here](https://github.com/AleksandarHaralanov/BarrierBlock/releases/latest).</b>
- Compiled on Adoptium JDK 8 and [Poseidon](https://github.com/RhysB/Project-Poseidon), fork of CB1060.

## Usage
When toggled on, placing an Obsidian block will convert it to a Barrier block. See instructions below.

By default, only OPs have permissions. Use PermissionsEx or similar plugins to grant groups the permission to toggle placement on/off and break barrier blocks.
- Command: `/barrier`
  - Aliases: `/b`, `/bb`, `/bar`
- Permissions:
  - `barrierblock.*` - Grants both permissions below.
    - `barrierblock.place` - Allows toggling of `/barrier` (and its aliases).
    - `barrierblock.break` - Allows breaking of Barrier blocks.

## Note if running [StairsFix](https://github.com/AleksandarHaralanov/StairsFix)
Use the [latest](https://github.com/AleksandarHaralanov/StairsFix/releases/latest) release of StairsFix to avoid stairs duplication.