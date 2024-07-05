# Barriers
Players can create invisible pass-through barrier blocks.

Can be used for multiple purposes, including:
- Stopping water and/or lava flow.
- Levitating signs.
- And more, <i>if you're creative</i>.

<b>Download latest release [here](https://github.com/AleksandarHaralanov/BarrierBlock/releases/latest).</b>
- Compiled on Adoptium JDK 8 and [Poseidon](https://github.com/RhysB/Project-Poseidon), fork of CB1060.

## Usage
By default, only OPs have permissions.

Use PermissionsEx or similar plugins to grant groups the permission to toggle placement on/off and break barrier blocks.
- Commands: 
  - `/barrier` or `/bar` - Requires permission - Toggles the placement of barrier blocks.
    - The block ID specified in `config.yml` must be a value between 1 and 255. If an invalid value is entered, it will default to 49 (Obsidian). When toggled on, placing this block will convert it into a barrier block.
  - `/barriers` - Does not require permission - Prints author, version, and a link to this repository.
- Permissions:
  - `barrierblock.*` - Grants both permissions below.
    - `barrierblock.place` - Allows usage of `/barrier` and its alias, `/bar`.
    - `barrierblock.break` - Allows breaking of barrier blocks.