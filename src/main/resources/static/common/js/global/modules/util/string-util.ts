module StringUtil {
    export const isValidity = (str: string, validCondition?: { minLength?: number, maxLength?: number, mustNotInStr? : string, mustInStr? : string}): boolean => {
        if (str === undefined || str === null || str === "") return false;
        return !(!!validCondition && (
                (!!validCondition.minLength && validCondition.minLength > str.length) ||
                (!!validCondition.maxLength && validCondition.maxLength < str.length) ||
                (!!validCondition.mustNotInStr && str.indexOf(validCondition.mustNotInStr) > -1) ||
                (!!validCondition.mustInStr && str.indexOf(validCondition.mustInStr) < 0)
            )
        );
    }
}