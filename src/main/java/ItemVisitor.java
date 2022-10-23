public interface ItemVisitor {

    default double visit(Weapon item){
        return 0.0;
    }
    default double visit(Armor item) {
        return 0.0;
    }
    default double visit(Shield item){
        return 0.0;
    }

    default double visit(MagicBag item){
        return 0.0;
    }
}
